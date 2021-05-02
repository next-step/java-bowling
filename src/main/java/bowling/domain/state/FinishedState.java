package bowling.domain.state;

import bowling.domain.Pins;
import bowling.domain.exception.CannotBowlException;

public abstract class FinishedState implements State {
    @Override
    public State stateAfterPitch(Pins pitch) {
        throw new CannotBowlException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
