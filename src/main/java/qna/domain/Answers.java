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
    private final List<Answer> answers = new ArrayList<>();

    public void add(final Answer answer) {
        this.answers.add(answer);
    }

    public List<DeleteHistory> deleteBy(final User loginUser) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            deleteHistories.add(answer.deleteBy(loginUser));
        }
        return deleteHistories;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
