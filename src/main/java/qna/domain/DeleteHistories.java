package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public DeleteHistories() {
    }

    public static DeleteHistories of(List<DeleteHistory> deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    public void addDeleteHistory(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public List<DeleteHistory> getHistories() {
        return deleteHistories;
    }
}
