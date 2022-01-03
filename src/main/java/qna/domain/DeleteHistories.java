package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories() {
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
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
