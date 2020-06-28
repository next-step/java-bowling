package qna.domain;

import qna.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        if (CollectionUtils.isEmpty(deleteHistories)) {
            this.deleteHistories = new ArrayList<>();
        }

        this.deleteHistories = new ArrayList<>(deleteHistories);
    }

    public static DeleteHistories of(List<DeleteHistory> deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    public static DeleteHistories mergeHistories(DeleteHistory targetHistory, DeleteHistories targetHistories) {
        List<DeleteHistory> mergedHistories = new ArrayList<>(targetHistories.deleteHistories);
        mergedHistories.add(targetHistory);
        return DeleteHistories.of(mergedHistories);
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
        return this.deleteHistories.containsAll(that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deleteHistories);
    }
}
