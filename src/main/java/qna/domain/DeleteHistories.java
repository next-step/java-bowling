package qna.domain;

import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    @OrderBy("createDate DESC")
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public DeleteHistories() {
        this(new ArrayList<>());
    }

    public List<DeleteHistory> list() {
        return deleteHistories;
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void addAll(DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.list());
    }

    public void addAll(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
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
}
