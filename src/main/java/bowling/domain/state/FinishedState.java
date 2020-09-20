package bowling.domain.state;

import bowling.domain.pin.Pin;

public abstract class FinishedState implements State {

    @Override
    public State bowl(Pin pin) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isEnd() {
        return true;
    }

}
