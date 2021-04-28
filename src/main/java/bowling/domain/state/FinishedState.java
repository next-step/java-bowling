package bowling.domain.state;

import bowling.domain.exception.CannotBowlException;

public abstract class FinishedState implements State {
    @Override
    public State stateAfterPitch(int pitch) {
        throw new CannotBowlException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
