package bowling.domain.state;

import bowling.domain.HitCount;
import bowling.exception.NoMoreCountingActionException;
import bowling.util.BowlingFixture;

public final class Ready extends Running {

    private Pins pins;

    private Ready() {
        this.pins = Pins.initialize();
    }

    public static final State initialize() {
        return new Ready();
    }

    @Override
    public final State bowl(final HitCount hitCount) {
        pins = pins.hit(hitCount);
        if (pins.isEmpty()) {
            return Strike.initialize();
        }
        return FirstBowl.from(hitCount.count());
    }

    @Override
    public int size() {
        return BowlingFixture.ZERO;
    }

    @Override
    public int firstCount() {
        throw new NoMoreCountingActionException();
    }

    @Override
    public int secondCount() {
        throw new NoMoreCountingActionException();
    }



}
