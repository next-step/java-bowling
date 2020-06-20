package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public DeleteHistories makeDeleteHistories() {
        DeleteHistories deleteHistories = new DeleteHistories();
        for (Answer answer : answers) {
            deleteHistories.add(answer.makeDeleteHistory());
        }
        return deleteHistories;
    }
}
