package qna.domain;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistoryList;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistoryList = deleteHistories;
    }

    public static DeleteHistories of(DeleteHistory... deleteHistories) {
        return new DeleteHistories(new LinkedList<>(Arrays.asList(deleteHistories)));
    }

    public DeleteHistories addAll(List<DeleteHistory> deleteHistories) {
        deleteHistoryList.addAll(deleteHistories);

        return this;
    }

    public List<DeleteHistory> getDeleteHistoryList() {
        return deleteHistoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeleteHistories)) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(deleteHistoryList, that.deleteHistoryList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistoryList);
    }
}
