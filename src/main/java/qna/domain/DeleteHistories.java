package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

// todo 형태가 Answers와 거의 유사하다
public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = Collections.unmodifiableList(deleteHistories);
    }

    public static DeleteHistories of(List<DeleteHistory> deleteHistories) {
        if (deleteHistories == null) {
            throw new IllegalArgumentException("invalid list: cannot be null");
        }

        return new DeleteHistories(deleteHistories);
    }

    public List<DeleteHistory> collect() {
        return deleteHistories;
    }

    public DeleteHistories prepend(DeleteHistory deleteHistory) {
        if (deleteHistory == null) {
            throw new IllegalArgumentException("invalid delete history: cannot be null");
        }

        final List<DeleteHistory> newHistories = new ArrayList<>(deleteHistories.size() + 1);
        newHistories.add(deleteHistory);
        newHistories.addAll(deleteHistories);
        return new DeleteHistories(newHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }

    @Override
    public String toString() {
        return "DeleteHistories{" +
                "deleteHistories=" + deleteHistories +
                '}';
    }
}
