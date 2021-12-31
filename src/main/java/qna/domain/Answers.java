package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private boolean deleted = false;

    private final List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void delete(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
        setDeleted(true);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
