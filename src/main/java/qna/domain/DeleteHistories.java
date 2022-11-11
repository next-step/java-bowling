package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(DeleteHistory deleteHistory) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(deleteHistory);
        return new DeleteHistories(deleteHistories);
    }

    public List<DeleteHistory> getHistories() {
        return deleteHistories;
    }

    public void join(DeleteHistories histories) {
        deleteHistories.addAll(histories.deleteHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories histories = (DeleteHistories) o;
        return Objects.equals(deleteHistories, histories.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }
}
