package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private long questionId;
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(long questionId) {
        this.questionId = questionId;
    }

    public void addHistory(ContentType contentType, User deletedBy) {
        deleteHistories.add(new DeleteHistory(contentType, questionId, deletedBy));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
