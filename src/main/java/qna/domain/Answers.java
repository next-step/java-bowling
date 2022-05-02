package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public List<DeleteHistory> deleteAnswerSoftly(User writer) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteAnswerSoftly(writer));
        }
        return deleteHistories;
    }

}
