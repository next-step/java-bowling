package qna.domain.deleteHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(DeleteHistory deleteHistory) {
        this(List.of(deleteHistory));
    }

    public DeleteHistories(DeleteHistory deleteHistory, List<DeleteHistory> deleteHistories) {
        this(map(deleteHistory, deleteHistories));
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    private static List<DeleteHistory> map(DeleteHistory deleteHistory, List<DeleteHistory> deleteHistories) {
        List<DeleteHistory> deleteHistoryList = new ArrayList<>();

        deleteHistoryList.add(deleteHistory);
        deleteHistoryList.addAll(deleteHistories);

        return deleteHistoryList;
    }

    public void saveTo(DeleteHistoryRepository deleteHistoryRepository) {
        deleteHistoryRepository.saveAll(this.deleteHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }
}
