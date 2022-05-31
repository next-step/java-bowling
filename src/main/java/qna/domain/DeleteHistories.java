package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    List<DeleteHistory> deleteHistories = new ArrayList<>();

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void add(List<DeleteHistory> histories) {
        deleteHistories.addAll(histories);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
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
