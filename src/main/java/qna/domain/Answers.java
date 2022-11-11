package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers(Answer... answers) {
        this(Arrays.asList(answers));
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void validateIfHasOthersAnswer(User loginUser) throws CannotDeleteException {
        Optional<Answer> othersAnswer = answers.stream()
                .filter(answer -> !answer.isOwner(loginUser))
                .findAny();
        if (othersAnswer.isPresent()) {
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
            Answer deletedAnswer = answer.delete();
            deleteHistories.add(deletedAnswer.generateDeleteHistory());
        }
        return new DeleteHistories(deleteHistories);
    }
}
