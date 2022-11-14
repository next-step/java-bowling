package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> histories = new ArrayList<>();
        for (Answer answer : answers) {
            histories.add(answer.delete(loginUser));
        }

        return histories;
    }
}
