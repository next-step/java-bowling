package bowling.domain.state.finish;

import bowling.domain.state.Pins;
import bowling.domain.state.State;
import bowling.exception.NoActionBowlException;

public abstract class Finish implements State {

    @Override
    public State bowl(Pins pins) {
        throw new NoActionBowlException();
    }

    @Override
    public final boolean isFinish() {
        return true;
    }

}
