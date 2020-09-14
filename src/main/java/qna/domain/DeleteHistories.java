package qna.domain;

import java.util.*;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(DeleteHistory... deleteHistories) {
        return new DeleteHistories(Arrays.asList(deleteHistories));
    }

    public DeleteHistories combine(DeleteHistories target) {
        List<DeleteHistory> combinedDeleteHistories = new ArrayList<>();
        combinedDeleteHistories.addAll(this.deleteHistories);
        combinedDeleteHistories.addAll(target.deleteHistories);

        return new DeleteHistories(combinedDeleteHistories);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
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
