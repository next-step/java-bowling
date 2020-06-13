package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;

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

    void removeAll() {
        this.answers.forEach(answer -> answer.delete());
        this.answers.clear();
    }

    boolean hasOtherAnswer(User writer) {
        return this.answers.stream()
            .anyMatch(answer -> !answer.isOwner(writer));
    }

    public List<Answer> getAnswer() {
        return null;
    }
}
