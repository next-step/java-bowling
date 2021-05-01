package bowling.domain.state;

import bowling.exception.InsufficientMissCountException;

public final class Miss extends Finish{

    private static final int MAX_COUNT = 10;

    private final int firstCount;
    private final int secondCount;

    private Miss(final int firstCount, final int secondCount) {
        validateSum(firstCount, secondCount);
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    private final void validateSum(final int firstCount, final int secondCount) {
        if(Math.addExact(firstCount, secondCount) >= MAX_COUNT) {
            throw new InsufficientMissCountException(firstCount, secondCount);
        }
    }

    public static final Miss from(final int firstCount, final int secondCount) {
        return new Miss(firstCount, secondCount);
    }
}
