package qna.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAll(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : getAnswers()) {
            deleteHistories.add(deleteAnswer(loginUser, answer));
        }
        return deleteHistories;
    }

    private DeleteHistory deleteAnswer(User loginUser, Answer answer) throws CannotDeleteException {
        return answer.delete(loginUser);
    }

}
