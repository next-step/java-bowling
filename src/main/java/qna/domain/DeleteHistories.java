package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistoryList;

    public DeleteHistories() {
        deleteHistoryList = new ArrayList<>();
    }

    public boolean addAll(DeleteHistories deleteHistories) {
        return this.deleteHistoryList.addAll(deleteHistories.deleteHistoryList);
    }

    public boolean add(DeleteHistory deleteHistory) {
        return deleteHistoryList.add(deleteHistory);
    }

    public List<DeleteHistory> toReadOnlyList() {
        return Collections.unmodifiableList(deleteHistoryList);
    }
}
