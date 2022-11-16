package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = new ArrayList<>(deleteHistories);
    }

    public DeleteHistories add(DeleteHistory deleteHistory) {
        List<DeleteHistory> newDeleteHistories = new ArrayList<>(deleteHistories);
        newDeleteHistories.add(deleteHistory);

        return new DeleteHistories(newDeleteHistories);
    }

    public List<DeleteHistory> histories() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
