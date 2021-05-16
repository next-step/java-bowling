package bowling.domain.state.finish;

import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.exception.NoMoreBowlException;

public abstract class Finish implements State {

    @Override
    public final State bowl(final Pins pins) {
        throw new NoMoreBowlException();
    }

    @Override
    public State bowl(final int pins) {
        throw new NoMoreBowlException();
    }

    @Override
    public final boolean isFinish() {
        return true;
    }

}
