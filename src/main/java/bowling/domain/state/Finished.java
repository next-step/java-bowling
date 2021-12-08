package bowling.domain.state;

import bowling.domain.Pin;

public abstract class Finished implements State {

    @Override
    public State bowl(Pin pin) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
