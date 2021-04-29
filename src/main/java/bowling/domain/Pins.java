package bowling.domain;

import bowling.exception.OverHitCountException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public final class Pins {

    private static final int MINIMUM_COUNT = 0;
    private static final int MAXIMUM_COUNT = 10;

    private static final List<Pins> CACHE;

    private int remain;

    static {
        CACHE = new ArrayList<>();
        for (int i = MINIMUM_COUNT; i <= MAXIMUM_COUNT; i++) {
            CACHE.add(new Pins(i));
        }
    }

    private Pins(final int remain) {
        this.remain = remain;
    }

    public static final Pins init() {
        return CACHE.get(MAXIMUM_COUNT);
    }

    public final Pins hit(final int hitCount) {
        validateOverCount(hitCount);
        return CACHE.get(Math.subtractExact(remain, hitCount));
    }

    private final void validateOverCount(final int hitCount) {
        if (remain < hitCount) {
            throw new OverHitCountException(hitCount, remain);
        }
    }

    public final boolean isClear() {
        if (remain == MINIMUM_COUNT) {
            return true;
        }
        return false;
    }

    public final int hittedCount() {
        return Math.subtractExact(MAXIMUM_COUNT, remain);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return remain == pins.remain;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remain);
    }


}
