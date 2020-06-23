package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class Answers {
    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    public void delete(User loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser, deleteHistories);
        }
    }

}
