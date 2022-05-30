package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        deleteHistories = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(Question question, User loginUser) throws CannotDeleteException {
        deleteHistories.add(new DeleteHistory(question, loginUser));
    }

    public void add(Answer answer, User loginUser) throws CannotDeleteException {
        deleteHistories.add(new DeleteHistory(answer, loginUser));
    }

    public void add(DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.value());
    }

    public List<DeleteHistory> value() {
        return Collections.unmodifiableList(deleteHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteHistories that = (DeleteHistories) o;

        return deleteHistories != null ? deleteHistories.equals(that.deleteHistories) : that.deleteHistories == null;
    }

    @Override
    public int hashCode() {
        return deleteHistories != null ? deleteHistories.hashCode() : 0;
    }
}
