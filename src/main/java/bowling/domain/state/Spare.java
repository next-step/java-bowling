package bowling.domain.state;

import bowling.exception.InputNumberOutOfBoundsException;
import bowling.exception.InsufficientSpareCountException;

import static bowling.util.BowlingFixture.*;
import static java.lang.Boolean.TRUE;

public final class Spare extends Finish {

    private final int firstCount;
    private final int secondCount;

    private Spare(final int firstCount, final int secondCount) {
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
        if (Math.addExact(firstCount, secondCount) != MAXIMUM_COUNT) {
            throw new InsufficientSpareCountException(firstCount, secondCount);
        }
    }

    public static final State of(final int firstCount, final int secondCount) {
        return new Spare(firstCount, secondCount);
    }

    @Override
    public final boolean isAllPinClear() {
        return TRUE;
    }
}
