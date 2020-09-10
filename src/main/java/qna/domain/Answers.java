package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.Embeddable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

    private List<Answer> answers;

    public Answers() {

    }

    private Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public DeleteHistories delete(User user) throws CannotDeleteException {
        validateOwner(user);

        return DeleteHistories.of(answers.stream()
                .map(Answer::delete)
                .toArray(DeleteHistory[]::new));
    }

    private void validateOwner(User user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateOwner(user);
        }
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
