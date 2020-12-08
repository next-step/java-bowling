package qna.domain;

import org.hibernate.annotations.Where;
import qna.exception.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    protected Answers() {
    }


    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void deleteAnswers(final User loginUser ,final DeleteHistories deleteHistories)  {
        answers.forEach(answer -> {
            try {
                answer.deleteAnswer(loginUser , deleteHistories);
            } catch (CannotDeleteException e) {
                e.printStackTrace();
            }
        });
    }
}
