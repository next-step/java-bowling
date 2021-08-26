package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Answers {
    private final List<Answer> answers;

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    public Answers() {
        answers = new ArrayList<>();
    }

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean isOwner(User loginUser) {
        return answers.stream()
                .filter(answer -> answer.isOwner(loginUser))
                .findFirst()
                .isPresent();

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

    public void add(Answer answer) {
        answers.add(answer);
    }
}
