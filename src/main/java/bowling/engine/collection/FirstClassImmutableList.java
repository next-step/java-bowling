package bowling.engine.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstClassImmutableList<T> extends AbstractFirstClassList<T> {
    private static final int NEW_ELEMENT_SIZE = 1;
    protected FirstClassImmutableList(List<T> collection) {
        super(Collections.unmodifiableList(collection));
    }

    @Override
    public FirstClassList<T> append(T t) {
        if (t == null) {
            throw new IllegalArgumentException("append null is not allowed");
        }

        final List<T> listBuffer = new ArrayList<>(size() + NEW_ELEMENT_SIZE);
        listBuffer.addAll(collection);
        listBuffer.add(t);
        return new FirstClassImmutableList<>(listBuffer);
    }
}
