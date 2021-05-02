package bowling.domain.state;

import bowling.exception.NoMoreCountingActionException;

import static bowling.util.BowlingFixture.ONE;
import static bowling.util.BowlingFixture.ALL_PIN_CLEAR;
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
        return ONE;
    }

    @Override
    public int firstCount() {
        return ALL_PIN_CLEAR;
    }

    @Override
    public int secondCount() {
        throw new NoMoreCountingActionException();
    }


}
