package bowling.engin;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface FirstClassCollection<T> {
    Collection<T> collect();
    Stream<T> stream();
    void forEach(Consumer<? super T> action);

    int size();

    T elementOf(int index);
    int indexOf(T t);
}
