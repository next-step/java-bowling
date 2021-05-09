package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.exception.NoActionBowlException;

public abstract class Finish implements State {

    @Override
    public Frame bowl(Pins pins) {
        throw new NoActionBowlException();
    }

    @Override
    public final boolean isFinish() {
        return true;
    }

}
