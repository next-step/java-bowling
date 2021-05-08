package qna.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setDeleted(boolean deleted) {
        for (Answer answer : answers) {
            answer.setDeleted(deleted);
        }
    }

    public void deleteHistoriesAdd(List<DeleteHistory> deleteHistories) {
        for (Answer answer : this.answers) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }
}
