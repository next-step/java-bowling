package qna.domain;

import java.util.ArrayList;
import java.util.List;

import qna.CannotDeleteException;

public class Answers {
    private final List<Answer> answers;

    public Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }
}
