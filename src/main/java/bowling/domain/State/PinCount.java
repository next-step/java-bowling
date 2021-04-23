package bowling.domain.State;

import java.util.HashMap;
import java.util.Map;

public class PinCount {

    private static final int MIN_PIN_COUNT = 0;

    private static final int MAX_PIN_COUNT = 10;

    private static final Map<Integer, PinCount> CACHE = new HashMap<>();

    public static final PinCount STRIKE = new PinCount(MAX_PIN_COUNT);

    public static final PinCount GUTTER = new PinCount(MIN_PIN_COUNT);

    private final int count;

    static {
        for (int i = MIN_PIN_COUNT; i <= MAX_PIN_COUNT; i++) {
            CACHE.put(i, new PinCount(i));
        }
    }

    private PinCount(int count) {
        if (count < MIN_PIN_COUNT || count > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("투구수는 0-10사이어야 합니다.");
        }
        this.count = count;
    }

    public static PinCount of(int count) {
        if (!CACHE.containsKey(count)) {
            throw new IllegalArgumentException("올바르지 않는 투구 수 입니다.");
        }
        return CACHE.get(count);
    }

    public static PinCount of(String count) {
        return of(Integer.parseInt(count));
    }

    public int count() {
        return count;
    }

    public String countInString() {
        return Integer.toString(count());
    }

    public boolean isStrike() {
        return count == MAX_PIN_COUNT;
    }

    public boolean isGutter() {
        return count == MIN_PIN_COUNT;
    }

    public int sumCount(PinCount secondPinCount) {
        return count + secondPinCount.count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PinCount pinCount1 = (PinCount) o;

        return count == pinCount1.count;
    }

    @Override
    public int hashCode() {
        return count;
    }
}
