package qna.domain;

import org.hibernate.annotations.Where;
import org.springframework.util.Assert;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    protected Answers() {
    }

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    static Answers empty() {
        return from(new ArrayList<>());
    }

    void add(Answer answer) {
        Assert.notNull(answer, "answer to add must not be null");
        this.answers.add(answer);
    }

    List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        validateOwner(loginUser);
        return deleteAll();
    }

    private List<DeleteHistory> deleteAll() {
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private void validateOwner(User loginUser) throws CannotDeleteException {
        Assert.notNull(loginUser, "loginUser must not be null");
        for (Answer answer : answers) {
            validateOwner(answer, loginUser);
        }
    }

    private void validateOwner(Answer answer, User loginUser) throws CannotDeleteException {
        if (answer.isNotOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
