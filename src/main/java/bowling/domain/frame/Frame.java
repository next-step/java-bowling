package bowling.domain.frame;

import bowling.domain.rolling.Rollings;

public abstract class Frame {
    Rollings rollingResults;

    public abstract void rollingBall(int pinCount);

    public boolean isRollable() {
        return rollingResults.isRollingPossible();
    }
}
