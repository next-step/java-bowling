package bowling.domain.state;

import bowling.exception.InputNegativeNumberException;
import bowling.exception.InsufficientSpareCountException;

public final class Spare extends Finish {

    private static final int MAXIMUM_COUNT = 10;
    private static final int MINIMUM_COUNT = 0;

    private final int firstCount;
    private final int secondCount;

    private Spare(final int firstCount, final int secondCount) {
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
        if (Math.addExact(firstCount, secondCount) != MAXIMUM_COUNT) {
            throw new InsufficientSpareCountException(firstCount, secondCount);
        }
    }

    public static final State of(final int firstCount, final int secondCount) {
        return new Spare(firstCount, secondCount);
    }
}
