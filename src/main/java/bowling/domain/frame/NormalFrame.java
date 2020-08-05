package bowling.domain.frame;

import bowling.domain.state.InitialState;

public class NormalFrame extends Frame {
    public NormalFrame() {
        state = new InitialState(false);
    }

    @Override
    public void rollingBall(int knockedDownPinCount) {
        state = state.roll(knockedDownPinCount);
    }
}
