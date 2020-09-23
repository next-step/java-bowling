package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private List<DeleteHistory> deletehistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deletehistories = deleteHistories;
    }

    public DeleteHistories() {
        new ArrayList<DeleteHistory>();
    }

    public List<DeleteHistory> getDeletehistories() {
        return deletehistories;
    }

    public void add(DeleteHistory deleteHistory) {
        deletehistories.add(deleteHistory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deletehistories, that.deletehistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deletehistories);
    }
}
