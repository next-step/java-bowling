package bowling.domain.state.running;

import bowling.domain.state.ThrowingState;

public abstract class RunningState implements ThrowingState {

    @Override
    public boolean isEnd() {
        return false;
    }
}
