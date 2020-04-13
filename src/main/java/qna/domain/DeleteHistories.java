package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    final List<DeleteHistory> deleteHistories;

    private DeleteHistories(final DeleteHistory deleteHistory) {
        this.deleteHistories = new ArrayList<>();
        this.deleteHistories.add(deleteHistory);
    }

    private DeleteHistories(final List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(final DeleteHistory deleteHistory) {
        return new DeleteHistories(deleteHistory);
    }

    public static DeleteHistories of(final List<DeleteHistory> deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    public void add(final DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public void addAll(final DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.getDeleteHistories());
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
