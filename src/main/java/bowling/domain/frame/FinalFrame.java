package bowling.domain.frame;

import bowling.domain.state.Pin;
import bowling.domain.state.State;

public class FinalFrame implements Frame {
    @Override
    public Frame bowl(Pin fallenPins) {
        return null;
    }

    @Override
    public Score calculateByBeforeScore(State beforeState) {
        return null;
    }

    @Override
    public State getState() {
        return null;
    }
}
