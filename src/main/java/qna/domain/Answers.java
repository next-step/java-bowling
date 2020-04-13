package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Answers {
    private final List<Answer> answers;

    static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    private Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }
}
