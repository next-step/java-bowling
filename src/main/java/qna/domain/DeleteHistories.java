package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    private DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public static DeleteHistories of() {
        return new DeleteHistories();
    }

    public DeleteHistories addHistory(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
        return this;
    }

    public DeleteHistories addHistories(List<DeleteHistory> histories) {
        deleteHistories.addAll(histories);
        return this;
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
