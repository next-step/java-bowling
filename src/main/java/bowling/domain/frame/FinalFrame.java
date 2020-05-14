package bowling.domain.frame;

import bowling.domain.rolling.FinalRollings;

public class FinalFrame implements  Frame {
    private final FinalRollings rollingResults;

    public FinalFrame() {
        this.rollingResults = FinalRollings.init();
    }

    public void rollingBall(int pinCount) {
        rollingResults.roll(pinCount);
    }

    public boolean isRollable() {
        return rollingResults.isRollable();
    }
}
