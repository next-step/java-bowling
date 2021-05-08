package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public final class KnockedPins {
    private static final int MIN_COUNT = 0;
    private static final int MAX_COUNT = 10;
    private static final String BELOW_MIN_COUNT_MESSAGE =
            String.format("쓰러뜨린 핀 개수는 %d 이상이어야 합니다.", MIN_COUNT);
    private static final String OVER_MAX_COUNT_MESSAGE =
            String.format("쓰러뜨린 핀 개수는 %d 이하여야 합니다.", MAX_COUNT);

    private static final Map<Integer, KnockedPins> knockedPinsCache = new HashMap<>();

    private final int count;

    private KnockedPins(final int count) {
        this.count = count;
    }

    static {
        IntStream.rangeClosed(MIN_COUNT, MAX_COUNT)
                .forEach(i -> knockedPinsCache.put(i, new KnockedPins(i)));
    }

    public static KnockedPins from(final int count) {
        validateMinCount(count);
        validateMaxCount(count);
        return knockedPinsCache.get(count);
    }

    private static void validateMinCount(final int count) {
        if (count < MIN_COUNT) {
            throw new IllegalArgumentException(BELOW_MIN_COUNT_MESSAGE);
        }
    }

    private static void validateMaxCount(final int count) {
        if (count > MAX_COUNT) {
            throw new IllegalArgumentException(OVER_MAX_COUNT_MESSAGE);
        }
    }

    public int count() {
        return count;
    }
}
