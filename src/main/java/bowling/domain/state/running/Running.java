package bowling.domain.state.running;

import bowling.domain.score.Score;
import bowling.domain.state.State;

public abstract class Running implements State {

    @Override
    public final boolean isFinish() {
        return false;
    }

    @Override
    public final Score score() {
        return Score.unavailable();
    }

    @Override
    public final boolean isAllPinClear() {
        return false;
    }
}
