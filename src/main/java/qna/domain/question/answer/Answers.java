package qna.domain.question.answer;

import java.util.List;
import java.util.Objects;

public class Answers {
    private final List<Answer> values;

    public Answers(List<Answer> answers) {
        this.values = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers = (Answers) o;
        return Objects.equals(values, answers.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "Answers{" +
                "values=" + values +
                '}';
    }
}
