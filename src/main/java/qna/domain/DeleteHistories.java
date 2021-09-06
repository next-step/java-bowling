package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        deleteHistories = new ArrayList<>();
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void addAll(DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.values());
    }

    public List<DeleteHistory> values() {
        return deleteHistories;
    }
}
