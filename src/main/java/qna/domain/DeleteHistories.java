package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    private static final String INPUT_IS_NULL = "입력 값이 null 입니다.";

    private List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        deleteHistories = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        if (deleteHistories == null) {
            throw new IllegalArgumentException(INPUT_IS_NULL);
        }
        this.deleteHistories = deleteHistories;
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void addAll(DeleteHistories deleteAnswers) {
        deleteHistories.addAll(deleteAnswers.values());
    }

    public List<DeleteHistory> values() {
        return deleteHistories;
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
}
