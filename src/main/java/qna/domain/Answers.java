package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public List<DeleteHistory> delete(User writer) throws CannotDeleteException
    {
        List<DeleteHistory> histories = new ArrayList<>();
        for (Answer answer : answers) {
            histories.add(answer.delete(writer));
        }

        return histories;
    }
}
