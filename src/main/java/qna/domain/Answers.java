package qna.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;


@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {
        this(new ArrayList<>());
    }

    public boolean areAllWrittenByWriter(User loginUser) {
        return answers.stream()
            .allMatch(a -> a.isOwner(loginUser));
    }

    public List<Answer> value() {
        return answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    private boolean hasNoAnswers() {
        return value().isEmpty();
    }

    public boolean isDeletable(User user) {
        return hasNoAnswers() || areAllWrittenByWriter(user);
    }
}
