package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() { }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void deleteAnswer(User loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteAnswer(loginUser));
        }
    }

    public List<Answer> getAnswers() {
        return unmodifiableList(answers);
    }
}
