package bowling.domain.rolling;

import bowling.domain.frame.State;

public class Rolling {

    private State state;

    private Rolling(int knockedDownPinCount, State state) {

    }

    public static final Rolling roll(Rolling before, int knockedDownPinCount) {
        return new Rolling(knockedDownPinCount, before.state);
    }

    public State getState() {
        return null;
    }
}
