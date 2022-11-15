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
    private List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean contains(Answer answer) {
        return answers.contains(answer);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete(User loginUser, DeleteHistories deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser, deleteHistories);
        }
    }

    public void checkAnswersOwner(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkAnswersOwnerIsLoginUser(loginUser);
        }
    }
}
