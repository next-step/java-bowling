package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories createOf(DeleteHistory deleteHistory, Deque<DeleteHistory> deleteHistories) {
        deleteHistories.addFirst(deleteHistory);

        return new DeleteHistories(new ArrayList<>(deleteHistories));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeleteHistories)) return false;

        DeleteHistories that = (DeleteHistories) o;

        return deleteHistories != null ? deleteHistories.equals(that.deleteHistories) : that.deleteHistories == null;
    }

    @Override
    public int hashCode() {
        return deleteHistories != null ? deleteHistories.hashCode() : 0;
    }
}
