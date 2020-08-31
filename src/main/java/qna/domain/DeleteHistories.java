package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public static DeleteHistories of(DeleteHistory... deleteHistories) {
        DeleteHistories histories = new DeleteHistories();
        for (DeleteHistory history : deleteHistories) {
            histories.add(history);
        }
        return histories;
    }

    public DeleteHistories add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
        return this;
    }

    public DeleteHistories addAll(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
        return this;
    }

    public List<DeleteHistory> toList() {
        return deleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return deleteHistories.equals(that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }
}
