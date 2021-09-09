package qna.domain;

import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers(Answer... answers) {
        this(Arrays.asList(answers));
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    public static Answers of(Answer... answers) {
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

    public List<DeleteHistory> deleteAll(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
        return toDeleteHistories();
    }

    private List<DeleteHistory> toDeleteHistories() {
        return Collections.unmodifiableList(
                answers.stream()
                        .map(Answer::toDeleteHistory)
                        .collect(Collectors.toList())
        );
    }
}
