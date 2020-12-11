package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import qna.CannotDeleteException;

public class Answers {

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(Answer answer) {
        List<Answer> answers = new ArrayList<>();
        answers.add(answer);
        this.answers = answers;
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers add(Answer answer) {
        answers.add(answer);
        return this;
    }

    public void validateAnswersOwner(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateAnswerOwner(loginUser);
        }
    }

    public DeleteHistories deleteAnswers(User loginUser) throws CannotDeleteException {
        validateAnswersOwner(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory delete = answer.delete(loginUser);
            deleteHistories.add(delete);
        }
        return new DeleteHistories(deleteHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answers answers1 = (Answers) o;
        return answers.equals(answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }
}
