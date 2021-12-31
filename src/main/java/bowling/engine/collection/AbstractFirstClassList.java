package bowling.engine.collection;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public abstract class AbstractFirstClassList<T> implements FirstClassList<T> {
    private static final int LAST_DIFF = 1;

    private final List<T> collection;

    protected AbstractFirstClassList(List<T> collection) {
        this.collection = collection;
    }

    @Override
    public List<T> collect() {
        return collection;
    }

    @Override
    public Stream<T> stream() {
        return collection.stream();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        collection.forEach(action);
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public T elementOf(int index) {
        return collection.get(index);
    }

    @Override
    public Optional<T> elementOfOptional(int index) {
        if (index < HEAD || index >= size()) {
            return Optional.empty();
        }

        return Optional.of(collection.get(index));
    }


    @Override
    public int indexOf(T t) {
        return collection.indexOf(t);
    }

    @Override
    public T head() {
        return elementOf(HEAD);
    }

    @Override
    public T last() {
        return elementOf(size() - LAST_DIFF);
    }

    public Optional<T> lastOptional() {
        if (collect().isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(elementOf(size() - LAST_DIFF));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractFirstClassList)) return false;
        AbstractFirstClassList<?> that = (AbstractFirstClassList<?>) o;
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
