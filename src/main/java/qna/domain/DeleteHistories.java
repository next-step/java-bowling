package qna.domain;

import java.util.*;
import java.util.function.Consumer;

public class DeleteHistories implements Iterable<DeleteHistory> {

    private final List<DeleteHistory> values;

    public DeleteHistories(DeleteHistory deleteHistory, DeleteHistories deleteHistories) {
        this(generateHistories(deleteHistory, deleteHistories));
    }

    private static List<DeleteHistory> generateHistories(DeleteHistory deleteHistory, DeleteHistories deleteHistories) {
        List<DeleteHistory> histories = new ArrayList<>();
        histories.add(deleteHistory);
        for (DeleteHistory history : deleteHistories) {
            histories.add(history);
        }
        return histories;
    }

    public DeleteHistories() {
        this(new ArrayList<>());
    }

    public DeleteHistories(List<DeleteHistory> values) {
        this.values = values;
    }

    @Override
    public Iterator<DeleteHistory> iterator() {
        return values.iterator();
    }

    @Override
    public void forEach(Consumer<? super DeleteHistory> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<DeleteHistory> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
