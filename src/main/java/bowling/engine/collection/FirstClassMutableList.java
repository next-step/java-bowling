package bowling.engine.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstClassMutableList<T> extends AbstractFirstClassList<T> {
    protected FirstClassMutableList(List<T> collection) {
        super(deepCopy(collection));
    }

    private static <T> List<T> deepCopy(List<T> collection) {
        List<T> newList = new ArrayList<>(collection.size());
        newList.addAll(collection);
        return newList;
    }

    @Override
    public List<T> collect() {
        return Collections.unmodifiableList(super.collect());
    }

    @Override
    public FirstClassList<T> append(T t) {
        super.collect()
                .add(t);
        return this;
    }

    public T setElement(int index, T t) {
        return super.collect()
                .set(index, t);
    }
}
