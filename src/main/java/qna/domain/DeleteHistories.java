package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    private DeleteHistories() {
    }

    public static DeleteHistories create() {
        return new DeleteHistories();
    }

    public void addHistory(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public DeleteHistories addHistory(DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.getDeleteHistories());

        return this;
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
