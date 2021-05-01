package bowling.domain.state;

import bowling.domain.HitCount;
import bowling.exception.InputNegativeNumberException;

import static bowling.util.BowlingFixture.*;

public final class FirstBowl extends Running {

    private final int firstCount;

    private FirstBowl(final int firstCount) {
        validateNegative(firstCount);
        this.firstCount = firstCount;
    }

    private final void validateNegative(final int firstCount) {
        if (firstCount < MINIMUM_COUNT) {
            throw new InputNegativeNumberException(firstCount);
        }
    }

    public static final State from(final int firstCount) {
        return new FirstBowl(firstCount);
    }

    @Override
    public final State bowl(final HitCount hitCount) {
        if (firstCount + hitCount.count() == MAXIMUM_COUNT) {
            return Spare.of(firstCount, hitCount.count());
        }
        return Miss.from(firstCount, hitCount.count());
    }
}
