package bowling.state.ready;

import bowling.state.State;

public abstract class Ready implements State {

    @Override
    public boolean isFinished() {
        return false;
    }
}
