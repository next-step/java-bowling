package bowling.util;

import java.util.List;
import java.util.NoSuchElementException;

public class ListUtils {

    private ListUtils() {
    }

    public static <T> T getLastElement(List<T> list) {
        if (list.isEmpty()) {
            throw new NoSuchElementException();
        }

        return list.get(list.size() - 1);
    }

}
