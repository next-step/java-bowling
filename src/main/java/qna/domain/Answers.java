package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public void validate(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validate(loginUser);
        }
    }

    public List<DeleteHistory> delete(List<DeleteHistory> deleteHistories) {
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete());
        }
        return deleteHistories;
    }
}
