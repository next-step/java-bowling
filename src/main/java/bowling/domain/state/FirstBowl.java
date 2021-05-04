package bowling.domain.state;

import bowling.exception.NoMoreCountingActionException;

import static bowling.util.BowlingFixture.*;

public final class FirstBowl extends Running {

    private final PinCount firstCount;

    private FirstBowl(final PinCount firstCount) {
        this.firstCount = firstCount;
    }

    public static final State from(final PinCount firstCount) {
        return new FirstBowl(firstCount);
    }

    @Override
    public final State bowl(final PinCount hitCount) {
        if (firstCount.isSpare(hitCount)) {
            return Spare.of(firstCount, hitCount);
        }
        return Miss.of(firstCount, hitCount);
    }

    @Override
    public final int size() {
        return ONE;
    }

    @Override
    public final int firstCount() {
        return firstCount.count();
    }

    @Override
    public final int secondCount() {
        throw new NoMoreCountingActionException();
    }

}
