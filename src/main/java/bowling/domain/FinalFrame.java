package bowling.domain;

import bowling.domain.state.State;

public class FinalFrame implements Frame{
    private State state;

    @Override
    public State bowl(int countOfPins) {
        this.state = this.state.bowl(countOfPins);
        return this.state;
    }

    @Override
    public Frame nextFrame() {
        return null;
    }

    @Override
    public State getState() {
        return this.state;
    }
}
