package bowling.domain.state.finish;

import bowling.domain.pin.PinCount;
import bowling.domain.state.State;

public abstract class Finished implements State {

    @Override
    public State bowl(PinCount hitCount) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public boolean isMiss() {
        return false;
    }

    @Override
    public boolean isCleanState() {
        return this.isFinish() && !this.isMiss();
    }
}
