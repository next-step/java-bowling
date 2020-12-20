package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return questionId == that.questionId &&
                Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, deleteHistories);
    }
}
