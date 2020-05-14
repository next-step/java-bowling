package bowling.domain.frame;

import bowling.domain.rolling.NormalRollings;

public class NormalFrame implements Frame {
    private final NormalRollings normalRollings;

    public NormalFrame() {
        normalRollings = NormalRollings.init();
    }

    public void rollingBall(int pinCount) {
        normalRollings.roll(pinCount);
    }

    public boolean isRollable() {
        return normalRollings.isRollable();
    }

}
