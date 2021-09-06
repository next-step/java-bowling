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
        answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> deleteAndGenerateHistories(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> answerDeleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory answerDeleteHistory = answer.deleteAndGenerateHistory(loginUser);
            answerDeleteHistories.add(answerDeleteHistory);
        }
        return answerDeleteHistories;
    }
}
