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
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void addDeleteHistories(List<DeleteHistory> histories) {
        this.answers.forEach(answer -> histories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())));
    }

    public boolean isAllOwner(User loginUser) {
        return answers.stream().allMatch(answer -> answer.isOwner(loginUser));
    }

    public void deleteAll() {
        answers.forEach(answer -> answer.setDeleted(true));
    }
}
