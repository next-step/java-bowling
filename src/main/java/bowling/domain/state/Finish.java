package bowling.domain.state;

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
