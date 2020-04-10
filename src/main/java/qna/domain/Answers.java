package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

class Answers {
    private final List<Answer> answers;

    Answers(List<Answer> answers) {
        this.answers = answers;
    }

    boolean isNotDeletable(User loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }

    List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }

}
