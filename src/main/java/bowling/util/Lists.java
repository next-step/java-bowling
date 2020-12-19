package bowling.util;

import java.util.List;
import java.util.Optional;

public class Lists {
    public static  <T> Optional<T> getAsOptional(List<T> list, int index) {
        if (list.size() - 1 < index) {
            return Optional.empty();
        }
        return Optional.of(list.get(index));
    }
}
