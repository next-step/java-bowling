package bowling.domain.frame;

import bowling.domain.rolling.State;

public class Score {
    public Score(State state, int knockedDownPinCount) {
    }

    public boolean isCalculateEnd() {
        return false;
    }
}
