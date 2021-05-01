package bowling.domain.state;

import bowling.exception.InputNegativeNumberException;
import bowling.exception.InsufficientMissCountException;

public final class Miss extends Finish{

    private static final int MINIMUM_COUNT = 0;
    private static final int MAXIMUM_COUNT = 10;

    private final int firstCount;
    private final int secondCount;

    private Miss(final int firstCount, final int secondCount) {
        validateNegative(firstCount, secondCount);
        validateSum(firstCount, secondCount);
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    private final void validateNegative(final int firstCount, final int secondCount) {
        if (firstCount < MINIMUM_COUNT) {
            throw new InputNegativeNumberException(firstCount);
        }
        if (secondCount < MINIMUM_COUNT) {
            throw new InputNegativeNumberException(secondCount);
        }
    }

    private final void validateSum(final int firstCount, final int secondCount) {
        if(Math.addExact(firstCount, secondCount) >= MAXIMUM_COUNT) {
            throw new InsufficientMissCountException(firstCount, secondCount);
        }
    }

    public static final Miss from(final int firstCount, final int secondCount) {
        return new Miss(firstCount, secondCount);
    }
}
