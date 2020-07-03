package bowling.state.domain;

import bowling.pin.domain.Pin;

public abstract class Finished implements State {
    @Override
    public State roll(Pin felled) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
