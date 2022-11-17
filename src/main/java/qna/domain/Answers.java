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
        this.answers.forEach(answer -> histories.add(answer.getDeleteHistory()));
    }

    public boolean isAllOwner(User loginUser) {
        return answers.stream().allMatch(answer -> answer.isOwner(loginUser));
    }

    public boolean hasAnswer(Answer answer) {
        return this.answers.contains(answer);
    }

    public boolean isAllDeleted() {
        return answers.stream().allMatch(Answer::isDeleted);
    }

    public void deleteAll() {
        answers.forEach(answer -> answer.setDeleted(true));
    }
}
