package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }
    public Answers(List<Answer> answerList) {
        this.answers = answerList;
    }

    public void validateAuthorsAreSame(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateAuthorAreSame(loginUser);
        }
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> deleteAll() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            deleteHistories.add(answer.delete());
        }
        return deleteHistories;
    }
}
