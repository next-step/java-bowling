package bowling.domain.rolling;

import bowling.domain.frame.State;

public class Rolling {
    private static final int PIN_COUNT_MAX = 10;

    private final State state;
    private final int knockedDownPinCount;

    public Rolling(State state, int knockedDownPinCount) {
        this.state = state;
        this.knockedDownPinCount = knockedDownPinCount;
    }

    public State getState() {
        return state;
    }

    public int getRemainPinCount() {
        return PIN_COUNT_MAX - knockedDownPinCount;
    }
}
