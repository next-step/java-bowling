package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public DeleteHistories deleteAll(DeleteHistories deleteHistories, LocalDateTime dateTime) {
        for (Answer answer : answers) {
            deleteHistories = answer.delete(deleteHistories, dateTime);
        }
        return deleteHistories;
    }

    public void validateOwner(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateOwner(loginUser);
        }
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }
}
