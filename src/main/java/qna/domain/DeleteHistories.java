package qna.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(DeleteHistory deleteHistory) {
        this.deleteHistories = Arrays.asList(deleteHistory);
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = Collections.unmodifiableList(deleteHistories);
    }

    public static DeleteHistories concat(DeleteHistories one, DeleteHistories theOther) {
        List<DeleteHistory> concat = Stream.concat(one.deleteHistories.stream(), theOther.deleteHistories.stream())
            .collect(Collectors.toList());

        return new DeleteHistories(concat);
    }

    public List<DeleteHistory> transformToCollection() {
        return deleteHistories;
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
        return deleteHistories.equals(that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deleteHistories);
    }
}
