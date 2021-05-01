package bowling.domain.state;

import bowling.exception.InputNumberOutOfBoundsException;
import bowling.exception.InsufficientMissCountException;

import static bowling.util.BowlingFixture.*;
import static java.lang.Boolean.FALSE;

public final class Miss extends Finish {

    private final int firstCount;
    private final int secondCount;

    private Miss(final int firstCount, final int secondCount) {
        validateSize(firstCount, secondCount);
        validateSum(firstCount, secondCount);
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    private final void validateSize(final int firstCount, final int secondCount) {
        if (firstCount < MINIMUM_COUNT || firstCount > MAXIMUM_COUNT) {
            throw new InputNumberOutOfBoundsException(firstCount);
        }
        if (secondCount < MINIMUM_COUNT || secondCount > MAXIMUM_COUNT) {
            throw new InputNumberOutOfBoundsException(secondCount);
        }
    }

    private final void validateSum(final int firstCount, final int secondCount) {
        if (Math.addExact(firstCount, secondCount) >= MAXIMUM_COUNT) {
            throw new InsufficientMissCountException(firstCount, secondCount);
        }
    }

    public static final Miss of(final int firstCount, final int secondCount) {
        return new Miss(firstCount, secondCount);
    }

    @Override
    public final boolean isAllPinClear() {
        return FALSE;
    }
}
