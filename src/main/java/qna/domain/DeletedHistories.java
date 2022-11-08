package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeletedHistories {
    private final List<DeleteHistory> deletedHistories;

    public DeletedHistories(List<DeleteHistory> deleteHistories) {
        this.deletedHistories = deleteHistories;
    }

    public List<DeleteHistory> deletedHistories() {
        return new ArrayList<>(deletedHistories);
    }
}
