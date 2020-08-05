package bowling.domain.frame;

import bowling.common.exception.InvalidThrowBallException;
import bowling.domain.state.InitialState;

public class FinalFrame extends Frame {
    public FinalFrame() {
        state = new InitialState(true);
    }

    @Override
    public void rollingBall(int knockedDownPinCount) {
        if (!state.isRollingPossible()) {
            throw new InvalidThrowBallException();
        }

        state = state.roll(knockedDownPinCount);
    }
}
