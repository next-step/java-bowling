package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        deleteHistories = new ArrayList<>();
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    public void record(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }
}
