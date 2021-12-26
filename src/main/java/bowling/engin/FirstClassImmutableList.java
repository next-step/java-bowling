package bowling.engin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FirstClassImmutableList<T> implements FirstClassList<T> {
    private final List<T> collection;

    protected FirstClassImmutableList(List<T> collection) {
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

    public T elementOf(int index) {
        return collection.get(index);
    }

    public int indexOf(T t) {
        return collection.indexOf(t);
    }

    @Override
    public FirstClassList<T> append(T t) {
        final List<T> listBuffer = new ArrayList<>(size() + 1);
        listBuffer.addAll(collection);
        listBuffer.add(t);
        return new FirstClassImmutableList<>(listBuffer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FirstClassImmutableList)) return false;
        FirstClassImmutableList<?> that = (FirstClassImmutableList<?>) o;
        return Objects.equals(collection, that.collection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collection);
    }

    @Override
    public String toString() {
        return "{" +
                "collection=" + collection +
                '}';
    }
}
