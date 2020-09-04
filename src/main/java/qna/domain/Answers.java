package qna.domain;

import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public List<DeleteHistory> deleteBy(User user) throws CannotDeleteException {
        validateOwner(user);

        return answers.stream()
                .map(Answer::deleteBy)
                .collect(Collectors.toList());
    }

    private void validateOwner(User user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateOwner(user);
        }
    }
}
