package qna.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;

@Embeddable
public class Answers extends AbstractEntity{

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean hasAnotherOwner(User loginUser) {
        return answers.stream()
            .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<DeleteHistory> delete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.forEach(answer -> deleteHistories.add(answer.delete()));
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Answers{" +
            "answers=" + answers +
            '}';
    }
}
