package qna.domain;

import qna.CannotDeleteException;

import java.util.Arrays;
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

    public Answers deleteAll(User loginUser) throws CannotDeleteException {
        validateDeletePossible(loginUser);
        answers.forEach(Answer::delete);
        return this;
    }

    private void validateDeletePossible(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }

    public List<DeleteHistory> toDeleteHistories() {
        return answers.stream()
                .map(Answer::toDeleteHistory)
                .collect(Collectors.toList());
    }
}
