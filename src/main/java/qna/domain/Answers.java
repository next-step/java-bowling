package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory answerDeleteHistory = answer.delete(loginUser);
            deleteHistories.add(answerDeleteHistory);
        }

        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public int answerCount() {
        return answers.size();
    }
}