package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Pin {

    private static final int MIN_COUNT = 0;
    private static final int MAX_COUNT = 10;
    private static final Map<Integer, Pin> CACHE = new HashMap<>();

    private final int count;

    static {
        IntStream.rangeClosed(MIN_COUNT, MAX_COUNT)
                        .forEach(i -> CACHE.put(i, new Pin(i)));
    }

    public Pin(int count) {
        validateRange(count);

        this.count = count;
    }

    private static void validateRange(int count) {
        if (count < MIN_COUNT || count > MAX_COUNT) {
            throw new IllegalArgumentException(MIN_COUNT + "~" + MAX_COUNT + "수를 입력해 주세요.");
        }
    }

    public static Pin of(int count) {
        validateRange(count);

        return CACHE.get(count);
    }

    public int count() {
        return count;
    }
}
