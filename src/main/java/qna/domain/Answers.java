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

    protected Answers() {
        this.answers = new ArrayList<>();
    }

    private List<Answer> validateOwner(List<Answer> answers, User user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.isOwner(user);
        }
        return answers;
    }

    public List<DeleteHistory> deleteAnswers(User user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : validateOwner(answers, user)) {
            deleteHistories.add(answer.delete());
        }
        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

}
