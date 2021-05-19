package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> delete(boolean deleted, User loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.isOwner(loginUser);
            deleteHistories.add(answer.delete(deleted));
        }
        return deleteHistories;
    }
}
