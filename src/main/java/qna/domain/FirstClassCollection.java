package qna.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FirstClassCollection<T> {
    private final List<T> collection;

    protected FirstClassCollection(List<T> collection) {
        this.collection = Collections.unmodifiableList(collection);
    }

    public List<T> collect() {
        return collection;
    }

    public Stream<T> stream() {
        return collection.stream();
    }

    public void forEach(Consumer<? super T> action) {
        collection.forEach(action);
    }

    public int size() {
        return collection.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstClassCollection<?> that = (FirstClassCollection<?>) o;
        return Objects.equals(collection, that.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collection);
    }

    @Override
    public String toString() {
        return "FirstClassCollection{" +
                "collection=" + collection +
                '}';
    }
}
