package bowling.domain.state;

import bowling.domain.HitCount;
import bowling.exception.InputNegativeNumberException;

public final class FirstBowl extends Running {

    private static final int MAX_COUNT = 10;
    public static final int MINIMUM_COUNT = 0;
    private final int firstCount;

    private FirstBowl(final int firstCount) {
        validateNegative(firstCount);
        this.firstCount = firstCount;
    }

    private final void validateNegative(int firstCount) {
        if (firstCount < MINIMUM_COUNT) {
            throw new InputNegativeNumberException(firstCount);
        }
    }

    public static final State from(final int firstCount) {
        return new FirstBowl(firstCount);
    }

    @Override
    public final State bowl(final HitCount hitCount) {
        if (firstCount + hitCount.count() == MAX_COUNT) {
            return Spare.of(firstCount, hitCount.count());
        }
        return Miss.from(firstCount, hitCount.count());
    }
}
