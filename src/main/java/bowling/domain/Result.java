package bowling.domain;

import bowling.domain.interfaces.State;

public enum Result implements State {
    MISS,
    STRIKE,
    SPARE;

    @Override
    public State bowl(Pins pins, int count) {
        return this;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
