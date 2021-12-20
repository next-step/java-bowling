package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = Collections.unmodifiableList(deleteHistories);
    }

    public static DeleteHistories from(List<DeleteHistory> deleteHistories) {
        if (deleteHistories == null) {
            throw new IllegalArgumentException("생성할 삭제 이력들이 null로 전달되었습니다.");
        }
        return new DeleteHistories(deleteHistories);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    public DeleteHistories prepend(DeleteHistory deleteHistory) {
        if (deleteHistory == null) {
            throw new IllegalArgumentException("전달된 삭제 이력이 null입니다.");
        }

        List<DeleteHistory> newHistories = new ArrayList<>(deleteHistories.size() + 1);
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
