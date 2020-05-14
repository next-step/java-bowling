package bowling.domain.frame;

import bowling.domain.rolling.FinalRolling;

public class FinalFrame implements  Frame {
    private final FinalRolling rollingResult;

    public FinalFrame() {
        this.rollingResult = FinalRolling.init();
    }

    public void rollingBall(int pinCount) {
        rollingResult.roll(pinCount);
    }

    public boolean isRollable() {
        return rollingResult.isRollable();
    }
}
