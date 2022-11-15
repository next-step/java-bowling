package qna.domain;

import org.aspectj.util.Reflection;
import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Answers() throws UnsupportedOperationException{
        throw new UnsupportedOperationException("no empty instance available");
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers(Answer... answers) {
        this(new ArrayList<>(Arrays.asList(answers)));
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void validateIfHasOthersAnswer(User loginUser) throws CannotDeleteException {
        boolean othersAnswer = answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
        if (othersAnswer) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public DeleteHistories deleteAll(User loginUser) throws CannotDeleteException {
        validateIfHasOthersAnswer(loginUser);
        answers.forEach(Answer::delete);
        return generateDeleteHistories();
    }

    public DeleteHistories generateDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.generateDeleteHistory());
        }
        return new DeleteHistories(deleteHistories);
    }

    private void print(){
        System.out.println("jackson");
    }

}
