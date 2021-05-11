package bowling.domain.state;

import bowling.exception.InvalidPinsSizeException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public final class Pins {

    private static final int EMPTY = 0;
    private static final int FULL = 10;
    private static final List<Pins> CACHE;

    private final int count;

    static {
        CACHE = new ArrayList<>();
        for (int count = EMPTY; count <= FULL; count++) {
            CACHE.add(new Pins(count));
        }
    }

    public static final Pins full() {
        return new Pins(FULL);
    }

    public static final Pins valueOf(final int count) {
        validateSize(count);
        return CACHE.get(count);
    }

    public Pins(final int count) {
        validateSize(count);
        this.count = count;
    }

    private static final void validateSize(final int count) {
        if (count < EMPTY || count > FULL) {
            throw new InvalidPinsSizeException();
        }
    }

    public final Pins hit(final int fallCount) {
        validateFallCount(fallCount);
        return CACHE.get(subtractExact(count, fallCount));
    }

    private final void validateFallCount(final int fallCount) {
        if (fallCount > count) {
            throw new InvalidPinsSizeException();
        }
    }

    public final boolean isMiss(final int fallCount) {
        return addExact(count, fallCount) < FULL;
    }

    public final boolean isSpare(final int fallCount) {
        return addExact(count, fallCount) == FULL;
    }

    public final boolean isStrike() {
        return count == FULL;
    }

    public final boolean isEmpty() {
        return count == EMPTY;
    }

    public final int count() {
        return count;
    }


}
