package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    protected DeleteHistories() {
        deleteHistories = new ArrayList<>();
    }

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of() {
        return new DeleteHistories(new ArrayList<>());
    }

    public static DeleteHistories of(List<DeleteHistory> deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    public void save(final DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(getDeleteHistories(), that.getDeleteHistories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDeleteHistories());
    }
}
