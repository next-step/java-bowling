package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this(new ArrayList<>());
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public void addAll(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
    }

    public List<DeleteHistory> deleteHistories() {
        return this.deleteHistories;
    }
}
