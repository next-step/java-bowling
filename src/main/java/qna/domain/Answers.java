package qna.domain;

import java.util.List;
import java.util.Objects;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isOwner(User owner) {
        return answers.stream()
                .map(it -> it.isOwner(owner))
                .filter(it -> it.equals(false))
                .findFirst()
                .orElse(true);
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
}
