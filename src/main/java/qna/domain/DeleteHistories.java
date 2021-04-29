package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistoryList;

    public DeleteHistories() {
        deleteHistoryList = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> deleteHistoryList) {
        this.deleteHistoryList = deleteHistoryList;
    }

    public boolean addAll(DeleteHistories deleteHistories) {
        return this.deleteHistoryList.addAll(deleteHistories.deleteHistoryList);
    }

    public boolean add(DeleteHistory deleteHistory) {
        return deleteHistoryList.add(deleteHistory);
    }

    public List<DeleteHistory> getReadOnlyList() {
        return Collections.unmodifiableList(deleteHistoryList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistoryList, that.deleteHistoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistoryList);
    }
}
