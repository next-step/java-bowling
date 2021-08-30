package qna.domain;

import org.hibernate.annotations.Where;

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
    List<Answer> values = new ArrayList<>();

    public void addAnswer(Answer answer, Question parentQuestion) {
        answer.toQuestion(parentQuestion);
        values.add(answer);
    }

    public boolean containsOnlyBy(User writer) {
        for (Answer answer : values) {
            if (!answer.isOwner(writer)) {
                return false;
            }
        }

        return true;
    }

    public List<DeleteHistory> delete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : values) {
            deleteHistories.add(answer.delete());
        }

        return deleteHistories;
    }
}
