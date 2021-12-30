package bowling.engine.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstClassImmutableList<T> extends AbstractFirstClassList<T> {
    protected FirstClassImmutableList(List<T> collection) {
        super(Collections.unmodifiableList(collection));
    }

    @Override
    public FirstClassList<T> append(T t) {
        final List<T> listBuffer = new ArrayList<>(size() + 1);
        listBuffer.addAll(collect());
        listBuffer.add(t);
        return new FirstClassImmutableList<>(listBuffer);
    }
}
