package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public void addHistory(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void addDeleteHistories(List<DeleteHistory> inputDeleteHistories) {
        deleteHistories.addAll(inputDeleteHistories);
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
