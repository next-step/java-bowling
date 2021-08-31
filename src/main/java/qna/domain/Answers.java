package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }


    public List<DeleteHistory> delete(User userId) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : this.answers) {
            deleteHistories.add(answer.delete(userId));
        }

        return Collections.unmodifiableList(deleteHistories);

    }
}
