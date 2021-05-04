package bowling.domain.state;

import bowling.exception.NoMoreCountingActionException;

import static bowling.util.BowlingFixture.ZERO;

public final class Ready extends Running {

    private PinCount pinCount;

    private Ready() {
        this.pinCount = PinCount.initialize();
    }

    public static final State initialize() {
        return new Ready();
    }

    @Override
    public final State bowl(final PinCount hitCount) {
        pinCount = pinCount.hit(hitCount);
        if (pinCount.isEmpty()) {
            return Strike.initialize();
        }
        return FirstBowl.from(hitCount);
    }

    @Override
    public final int size() {
        return ZERO;
    }

    @Override
    public final int firstCount() {
        throw new NoMoreCountingActionException();
    }

    @Override
    public final int secondCount() {
        throw new NoMoreCountingActionException();
    }



}
