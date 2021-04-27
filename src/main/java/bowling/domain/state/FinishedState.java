package bowling.domain.state;

import bowling.domain.exception.CannotBowlException;

public abstract class FinishedState implements State {
    @Override
    public State bowl(int pitch) {
        throw new CannotBowlException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
