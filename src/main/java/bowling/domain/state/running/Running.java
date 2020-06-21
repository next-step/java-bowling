package bowling.domain.state.running;

import bowling.domain.state.State;

public abstract class Running implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean isMiss() {
        return false;
    }
}
