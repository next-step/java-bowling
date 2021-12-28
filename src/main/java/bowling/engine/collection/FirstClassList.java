package bowling.engine.collection;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface FirstClassList<T> {
    int HEAD = 0;

    List<T> collect();
    Stream<T> stream();
    void forEach(Consumer<? super T> action);

    int size();

    T elementOf(int index);
    int indexOf(T t);

    T head();
    T last();

    FirstClassList<T> append(T t);
}
