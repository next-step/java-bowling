package qna.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Answers {

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers(Answer... answers) {
        this(Arrays.asList(answers));
    }

    public static Answers of(List<Answer> answers){
        return new Answers(answers);
    }

    public static Answers of(Answer... answers){
        return new Answers(answers);
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

    public Answers deleteAll() {
        return null;
    }
}
