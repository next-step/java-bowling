package bowling.domain.state;

import bowling.exception.InputNumberOutOfBoundsException;
import bowling.exception.InputOverHitCountException;
import java.util.ArrayList;
import java.util.List;

import static bowling.util.BowlingFixture.*;

public final class PinCount {

    private static final List<PinCount> CACHE;

    private final int count;

    static {
        CACHE = new ArrayList<>();
        for (int count = MINIMUM_COUNT; count <= MAXIMUM_COUNT; count++) {
            CACHE.add(new PinCount(count));
        }
    }

    public static final PinCount initialize() {
        return CACHE.get(MAXIMUM_COUNT);
    }

    public static final PinCount empty() {
        return CACHE.get(MINIMUM_COUNT);
    }

    public static final PinCount valueOf(final int count) {
        validateBounds(count);
        return CACHE.get(count);
    }

    private PinCount(final int count) {
        validateBounds(count);
        this.count = count;
    }

    private static final void validateBounds(final int count) {
        if (count < MINIMUM_COUNT || count > MAXIMUM_COUNT) {
            throw new InputNumberOutOfBoundsException(count);
        }
    }

    public final PinCount hit(final PinCount other) {
        validateHitCount(other);
        return CACHE.get(Math.subtractExact(this.count, other.count));
    }

    private final void validateHitCount(final PinCount other) {
        if (this.count < other.count) {
            throw new InputOverHitCountException(this.count, other.count);
        }
    }

    public final boolean isSpare(final PinCount other) {
        return Math.addExact(count, other.count) == MAXIMUM_COUNT;
    }

    public final boolean isMiss(final PinCount other) {
        return Math.addExact(count, other.count) < MAXIMUM_COUNT;
    }

    public final boolean isEmpty() {
        return count <= MINIMUM_COUNT;
    }

    public final int count() {
        return count;
    }
}
