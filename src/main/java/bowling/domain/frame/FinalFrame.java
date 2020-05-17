package bowling.domain.frame;

import bowling.common.exception.InvalidThrowBallException;
import bowling.domain.rolling.FinalRollings;

public class FinalFrame extends Frame {
    public FinalFrame() {
        this.rollingResults = FinalRollings.init();
    }

    @Override
    public void rollingBall(int pinCount) {
        if (!rollingResults.isRollingPossible()) {
            throw new InvalidThrowBallException();
        }

        rollingResults.roll(pinCount);
    }

}
