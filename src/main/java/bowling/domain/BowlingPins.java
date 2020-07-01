package bowling.domain;

public class BowlingPins {
    private static final int MAX_PINS = 10;
    private final int number;

    private BowlingPins(final int number) {
        this.number = number;
    }

    public static BowlingPins of(final int number) {
        if (number < 0 || number > MAX_PINS) {
            throw new IllegalArgumentException("볼링핀 숫자는 0과 10사이여야 합니다.");
        }
        return BowlingPinsCache.cache[number];
    }

    public boolean isMax() {
        return this.number == MAX_PINS;
    }

    public boolean isNotAddable(final BowlingPins bowlingPins) {
        return this.number + bowlingPins.number > MAX_PINS;
    }

    private static class BowlingPinsCache {
        private static final BowlingPins[] cache;

        static {
            cache = new BowlingPins[MAX_PINS + 1];
            for (int i = 1; i <= MAX_PINS; i++) {
                cache[i] = new BowlingPins(i);
            }
        }

        private BowlingPinsCache() {
        }
    }
}

