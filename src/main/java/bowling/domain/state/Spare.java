package bowling.domain.state;

import bowling.exception.InsufficientSpareCountException;

import static bowling.util.BowlingFixture.*;

public final class Spare extends Finish {

    private final PinCount firstCount;
    private final PinCount secondCount;

    private Spare(final PinCount firstCount, final PinCount secondCount) {
        validateSpare(firstCount, secondCount);
        this.firstCount = firstCount;
        this.secondCount = secondCount;
    }

    private final void validateSpare(final PinCount firstCount, final PinCount secondCount) {
        if (!firstCount.isSpare(secondCount)) {
            throw new InsufficientSpareCountException(firstCount.count(), secondCount.count());
        }
    }

    public static final State of(final PinCount firstCount, final PinCount secondCount) {
        return new Spare(firstCount, secondCount);
    }

    @Override
    public final boolean isAllPinClear() {
        return true;
    }

    @Override
    public int size() {
        return TWO;
    }

    @Override
    public int firstCount() {
        return firstCount.count();
    }

    @Override
    public int secondCount() {
        return secondCount.count();
    }

}
