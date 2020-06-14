package qna.domain.question;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;
import qna.domain.history.DeleteHistory;
import qna.domain.user.User;

@Embeddable
public class QuestionAnswers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    protected QuestionAnswers() {
    }

    void add(Answer answer) {
        this.answers.add(answer);
    }

    List<DeleteHistory> deleteAllBy(User requestUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer: this.answers) {
            deleteHistories.add(answer.deleteBy(requestUser));
        }

        answers.clear();
        return deleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        QuestionAnswers that = (QuestionAnswers) o;
        return Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
