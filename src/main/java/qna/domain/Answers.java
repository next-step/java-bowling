package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    Answers() {
        answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void delete(User loginUser) {
        if (!isDeletable(loginUser)) {
            throw new IllegalArgumentException("잘못된 loginUser");
        }

        for (Answer answer : answers) {
            answer.setDeleted(true);
        }
    }

    public List<Answer> answers() {
        return answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }

    private boolean isDeletable(User loginUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
    }
}
