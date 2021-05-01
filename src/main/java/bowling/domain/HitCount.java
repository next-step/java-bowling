package bowling.domain;

import bowling.exception.InputNumberOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

import static bowling.util.BowlingFixture.*;

public final class HitCount {

    private static final List<HitCount> CACHE;

    private final int count;

    static {
        CACHE = new ArrayList<>();
        for (int count = MINIMUM_COUNT; count <= MAXIMUM_COUNT; count++) {
            CACHE.add(new HitCount(count));
        }
    }

    public static final HitCount valueOf(final int count) {
        validateBounds(count);
        return CACHE.get(count);
    }

    private HitCount(final int count) {
        validateBounds(count);
        this.count = count;
    }

    private static final void validateBounds(final int count) {
        if (count < MINIMUM_COUNT || count > MAXIMUM_COUNT) {
            throw new InputNumberOutOfBoundsException(count);
        }
    }

    public final int count() {
        return count;
    }
}
