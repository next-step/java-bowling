package bowling.domain;

import bowling.domain.state.State;

public class FinalFrame implements Frame{
    private static final int INIT_REMAIN_NUMBER = 3;

    @Override
    public State bowl(int countOfPins) {
        return null;
    }

    @Override
    public Frame nextFrame() {
        return null;
    }
}
