package bowling.domain.frame;

import bowling.domain.rolling.NormalRollings;

public class NormalFrame extends Frame {
    public NormalFrame() {
        this.rollingResults = NormalRollings.init();
    }

    @Override
    public void rollingBall(int pinCount) {
        this.rollingResults.roll(pinCount);
    }
}
