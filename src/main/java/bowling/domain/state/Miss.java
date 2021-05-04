package bowling.domain.state;

import bowling.exception.InsufficientMissCountException;

import static bowling.util.BowlingFixture.*;

public final class Miss extends Finish {

    private final PinCount firstCount;
    private final PinCount secondCount;

    private Miss(final PinCount firstCount, final PinCount secondCount) {
        validateMiss(firstCount, secondCount);
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    private final void validateMiss(final PinCount firstCount, final PinCount secondCount) {
        if (!firstCount.isMiss(secondCount)) {
            throw new InsufficientMissCountException(firstCount.count(), secondCount.count());
        }
    }

    public static final State of(final PinCount firstCount, final PinCount secondCount) {
        return new Miss(firstCount, secondCount);
    }

    @Override
    public final boolean isAllPinClear() {
        return false;
    }

    @Override
    public final int size() {
        return TWO;
    }

    @Override
    public final int firstCount() {
        return firstCount.count();
    }

    @Override
    public final int secondCount() {
        return secondCount.count();
    }

}
