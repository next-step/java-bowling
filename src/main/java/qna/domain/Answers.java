package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void isAnotherUserAnswers(User writer) {
        this.answers.forEach(answer -> answer.isAnotherUserAnswer(writer));
    }

    public void addDeleteHistories(List<DeleteHistory> deleteHistories) {
        this.answers.stream()
                .peek(answer -> answer.setDeleted(true))
                .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                .forEach(deleteHistories::add);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
