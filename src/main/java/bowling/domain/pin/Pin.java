package bowling.domain.pin;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Pin {

    private static final int MAX_HIT_COUNT = 10;
    private static final int MIN_HIT_COUNT = 0;

    private static final Map<Integer, Pin> CACHED_PINS;

    static {
        CACHED_PINS = IntStream.rangeClosed(MIN_HIT_COUNT, MAX_HIT_COUNT)
                .mapToObj(Pin::new)
                .collect(Collectors.toMap(pin -> pin.hitCount, pin -> pin));
    }

    private final int hitCount;

    private Pin(int hitCount) {
        checkRangeHitCount(hitCount);
        this.hitCount = hitCount;
    }

    private static void checkRangeHitCount(int hitCount) {
        if (hitCount < MIN_HIT_COUNT || hitCount > MAX_HIT_COUNT) {
            throw new IllegalRangeOfHitCountException();
        }
    }

    public static Pin from(int hitCount) {
        return Optional.ofNullable(CACHED_PINS.get(hitCount))
                .orElseThrow(IllegalRangeOfHitCountException::new);
    }

    public Pin plus(Pin other) {
        return from(hitCount + other.hitCount);
    }

    public boolean isAllHit() {
        return hitCount == MAX_HIT_COUNT;
    }

    public boolean isNoneHit() {
        return hitCount == MIN_HIT_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return hitCount == pin.hitCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hitCount);
    }
}
