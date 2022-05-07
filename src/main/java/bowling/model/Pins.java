package bowling.model;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Pins {

    private static final int MINIMUM_COUNT = 0;
    private static final int MAXIMUM_COUNT = 10;
    private static final Map<Integer, Pins> CACHE = IntStream.rangeClosed(MINIMUM_COUNT, MAXIMUM_COUNT)
            .mapToObj(Pins::new)
            .collect(Collectors.toMap(pins -> pins.count, pins -> pins));

    public static final Pins MAX = from(MAXIMUM_COUNT);
    public static final Pins ZERO = from(MINIMUM_COUNT);

    private final int count;

    private Pins(int count) {
        validate(count);
        this.count = count;
    }

    public static Pins from(int count) {
        return CACHE.getOrDefault(count, new Pins(count));
    }

    public Pins sum(Pins pins) {
        if (isGreaterThanMax(this.count + pins.count)) {
            throw new IllegalArgumentException(String.format("pins(%s) cannot be added to pins(%s)", pins, this));
        }
        return from(this.count + pins.count);
    }

    private void validate(int count) {
        if (isLessThanMin(count) || isGreaterThanMax(count)) {
            throw new IllegalArgumentException(String.format("count(%d) must be between %d and %d", count, MINIMUM_COUNT, MAXIMUM_COUNT));
        }
    }

    private boolean isGreaterThanMax(int count) {
        return MAXIMUM_COUNT < count;
    }

    private boolean isLessThanMin(int count) {
        return count < MINIMUM_COUNT;
    }

    @Override
    public String toString() {
        return "Pins{" +
                "count=" + count +
                '}';
    }
}
