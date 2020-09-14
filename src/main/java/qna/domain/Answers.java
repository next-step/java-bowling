package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public void validateAnswersWhenDeleting(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateOwnerWhenDeleting(loginUser);
        }
    }

    public List<DeleteHistory> deleteAnswers() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.forEach(answer -> deleteHistories.add(answer.delete()));
        return deleteHistories;
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }
}
