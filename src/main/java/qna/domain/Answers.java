package qna.domain;

import java.util.List;

public class Answers {

    private List<Answer> answers = null;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void deleteAll(List<DeleteHistory> deleteHistories) {
        for (Answer answer : answers) {
            answer.delete(deleteHistories);
        }
    }
}
