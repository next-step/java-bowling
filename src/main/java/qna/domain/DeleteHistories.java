package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = Collections.unmodifiableList(deleteHistories);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    public DeleteHistories addQuestion(Question question) {
        List<DeleteHistory> deleteHistories = new ArrayList<>(this.deleteHistories);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question, LocalDateTime.now()));
        return new DeleteHistories(deleteHistories);
    }

    public DeleteHistories addAnswer(Answer answer) {
        List<DeleteHistory> deleteHistories = new ArrayList<>(this.deleteHistories);
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer, LocalDateTime.now()));
        return new DeleteHistories(deleteHistories);
    }
}
