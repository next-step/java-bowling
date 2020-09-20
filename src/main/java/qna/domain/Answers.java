package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public List<DeleteHistory> deleteAnswers(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }
}
