package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }
    public DeleteHistories() {
        this.deleteHistories = new ArrayList();
    }

    public void add(Optional<Integer> idx, DeleteHistory deleteHistory) {
        if (idx.isPresent()) {
            this.deleteHistories.add(idx.get(), deleteHistory);
            return;
        }
        this.deleteHistories.add(deleteHistory);
        return;
    }

    public void saveAll(DeleteHistoryRepository deleteHistoryRepository) {
        deleteHistoryRepository.saveAll(this.deleteHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistories, that.deleteHistories);
    }

    @Override
    public String toString() {
        return "DeleteHistories{" +
                "deleteHistories=" + deleteHistories +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }
}
