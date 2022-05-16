package qna.domain;

import java.time.LocalDateTime;
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
    private List<Answer> answers = new ArrayList<>();

    public List<Answer> get() {
        return answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean hasDifferentOwner(User writer) {
        return answers.stream()
            .anyMatch(answer -> !answer.isOwner(writer));
    }

    public List<DeleteHistory> deleteAll() {
        answers.forEach(Answer::delete);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(DeleteHistory.of(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }

        return deleteHistories;
    }
}
