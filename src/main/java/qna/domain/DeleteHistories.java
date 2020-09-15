package qna.domain;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(DeleteHistory... deleteHistories) {
        return new DeleteHistories(Arrays.asList(deleteHistories));
    }

    public DeleteHistories combine(DeleteHistories target) {
        List<DeleteHistory> combinedDeleteHistories = Stream
                .concat(deleteHistories.stream(), target.deleteHistories.stream())
                .collect(Collectors.toList());

        return new DeleteHistories(combinedDeleteHistories);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }

    public void iterateDeleteHistories(Consumer<DeleteHistory> deleteHistoryConsumer) {
        deleteHistories.forEach(deleteHistoryConsumer::accept);
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
