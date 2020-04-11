package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(User loginUser) throws CannotDeleteException {
        for(Answer answer : answers) {
             answer.delete(loginUser);
        }
    }

    public List<DeleteHistory> getDeleteAnswersHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for(Answer answer : answers) {
            deleteHistories.add(answer.getDeleteAnswerHistory());
        }

        return deleteHistories;
    }
}
