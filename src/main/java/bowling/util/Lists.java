package bowling.util;

import java.util.List;
import java.util.Optional;

public class Lists {
    public static  <T> Optional<T> getAsOptional(List<T> list, int index) {
        return Optional.ofNullable(getOrNull(list, index));
    }
    public static  <T> T getOrNull(List<T> list, int index) {
        if (list.size() - 1 < index) {
            return null;
        }
        return list.get(index);
    }
}
