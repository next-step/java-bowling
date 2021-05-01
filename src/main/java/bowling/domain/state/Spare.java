package bowling.domain.state;

import bowling.exception.InsufficientSpareCountException;

public final class Spare extends Finish {

    private static final int MAX_COUNT = 10;

    private final int firstCount;
    private final int secondCount;

    private Spare(final int firstCount, final int secondCount) {
        validateSum(firstCount, secondCount);
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    private final void validateSum(final int firstCount, final int secondCount) {
        if (Math.addExact(firstCount, secondCount) != MAX_COUNT) {
            throw new InsufficientSpareCountException(firstCount, secondCount);
        }
    }

    public static final State of(final int firstCount, final int secondCount) {
        return new Spare(firstCount, secondCount);
    }
}
