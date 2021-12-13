package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Answers {
    public static final Answers EMPTY = new Answers(Collections.emptyList());

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public static Answers of(List<Answer> answers) {
        if (answers == null) {
            throw new IllegalArgumentException("invalid answers: cannot be null");
        }

        return new Answers(answers);
    }

    public List<Answer> collect() {
        return answers;
    }

    public Answers append(Answer answer) {
        if (answer == null) {
            throw new IllegalArgumentException("invalid answer: cannot be null");
        }

        List<Answer> newAnswers = new ArrayList<>(answers.size() + 1);
        newAnswers.addAll(answers);
        newAnswers.add(answer);
        return new Answers(newAnswers);
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

    @Override
    public String toString() {
        return "Answers{" +
                "answers=" + answers +
                '}';
    }
}
