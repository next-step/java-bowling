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

    public void delete() {
        for (Answer answer : this.answers) {
            answer.delete();
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isEnableDeletedBy(final User loginUser) {
        for (Answer answer : this.answers) {
            if (!answer.isOwner(loginUser)) {
                return false;
            }
        }
        return true;
    }
}
