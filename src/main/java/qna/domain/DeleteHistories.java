package qna.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }

    public DeleteHistories append(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
        return new DeleteHistories(deleteHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }

    @Override
    public String toString() {
        return "DeleteHistories{" +
                "deleteHistories=" + deleteHistories +
                '}';
    }
}
