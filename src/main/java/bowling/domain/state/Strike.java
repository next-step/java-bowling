package bowling.domain.state;

import bowling.exception.NoMoreCountingActionException;
import bowling.util.BowlingFixture;

import static bowling.util.BowlingFixture.ONCE;
import static bowling.util.BowlingFixture.STRIKE;
import static java.lang.Boolean.TRUE;

public final class Strike extends Finish {

    private Strike() {
    }

    public static final State initialize() {
        return new Strike();
    }

    @Override
    public final boolean isAllPinClear() {
        return TRUE;
    }

    @Override
    public int size() {
        return ONCE;
    }

    @Override
    public int firstCount() {
        return STRIKE;
    }

    @Override
    public int secondCount() {
        throw new NoMoreCountingActionException();
    }


}
