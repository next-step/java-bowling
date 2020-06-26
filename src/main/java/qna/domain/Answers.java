package qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import qna.CannotDeleteException;

public class Answers {

    private final List<Answer> answers;

    public Answers(final List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public static Answers create(Answer... answers) {
        return new Answers(Arrays.asList(answers));
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> deleteAll(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteByUser(loginUser));
        }

        return deleteHistories;
    }
}
