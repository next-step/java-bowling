package qna.domain;

import qna.CannotDeleteException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public List<DeleteHistory> delete(User user) throws CannotDeleteException {
        validateOwner(user);

        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private void validateOwner(User user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateOwner(user);
        }
    }
}
