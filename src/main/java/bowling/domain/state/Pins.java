package bowling.domain.state;

import bowling.domain.HitCount;
import bowling.exception.InputOverHitCountException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import static bowling.util.BowlingFixture.*;

public final class Pins {

    private static final List<Pins> CACHE;

    private final int pinCount;

    static {
        CACHE = new ArrayList<>();
        for (int count = MINIMUM_COUNT; count <= MAXIMUM_COUNT; count++) {
            CACHE.add(new Pins(count));
        }
    }

    private Pins(final int pinCount) {
        this.pinCount = pinCount;
    }

    public static final Pins initialize() {
        return CACHE.get(MAXIMUM_COUNT);
    }

    public final Pins hit(final HitCount hitCount) {
        validateCount(hitCount);
        return CACHE.get(Math.subtractExact(pinCount, hitCount.count()));
    }

    private final void validateCount(final HitCount hitCount) {
        if (hitCount.count() > pinCount) {
            throw new InputOverHitCountException(hitCount.count(), pinCount);
        }
    }

    public final boolean isEmpty() {
        if (pinCount <= MINIMUM_COUNT) {
            return TRUE;
        }
        return FALSE;
    }
}
