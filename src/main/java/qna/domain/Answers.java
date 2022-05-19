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
    private final List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public int size() {
        return answers.size();
    }

    public void delete(User loginUser, DeleteHistories deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
            deleteHistories.add(answer.makeDeleteHistory(loginUser));
        }
    }
}
