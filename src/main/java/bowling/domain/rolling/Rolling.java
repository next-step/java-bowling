package bowling.domain.rolling;

import bowling.domain.frame.State;

public class Rolling {
    private State state;
    private int knockedDownPinCount;

    public Rolling(State state, int knockedDownPinCount) {
        this.state = state;
        this.knockedDownPinCount = knockedDownPinCount;
    }

    public State getState() {
        return state;
    }
}
