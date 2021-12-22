package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(Question question) {
        this.answers = question.getAnswers();
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }

        return Collections.unmodifiableList(deleteHistories);
    }
}
