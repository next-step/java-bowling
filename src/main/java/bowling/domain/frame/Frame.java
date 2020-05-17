package bowling.domain.frame;

import bowling.domain.rolling.Rollings;

import java.util.List;

public abstract class Frame {
    Rollings rollingResults;

    public abstract void rollingBall(int pinCount);

    public boolean isRollable() {
        return rollingResults.isRollingPossible();
    }

    public List<String> getStates() {
        return rollingResults.getStates();
    }
}
