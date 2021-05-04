package bowling.domain.state;

import bowling.exception.NoMoreBowlActionsException;

public abstract class Finish implements State {

    @Override
    public final boolean isFinish() {
        return true;
    }

    @Override
    public final State bowl(final PinCount pinCount) {
        throw new NoMoreBowlActionsException();
    }

}
