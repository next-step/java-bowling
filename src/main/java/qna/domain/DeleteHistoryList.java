package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistoryList {

    private List<DeleteHistory> deleteHistoryList;

    public DeleteHistoryList() {
        deleteHistoryList = new ArrayList<>();
    }

    public DeleteHistoryList(List<DeleteHistory> deleteHistoryList) {
        this.deleteHistoryList = deleteHistoryList;
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistoryList.add(deleteHistory);
    }

    public List<DeleteHistory> value() {
        return Collections.unmodifiableList(deleteHistoryList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteHistoryList that = (DeleteHistoryList) o;

        return deleteHistoryList != null ? deleteHistoryList.equals(that.deleteHistoryList) : that.deleteHistoryList == null;
    }

    @Override
    public int hashCode() {
        return deleteHistoryList != null ? deleteHistoryList.hashCode() : 0;
    }
}
