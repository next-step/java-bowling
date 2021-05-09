package bowling.domain.state;

import bowling.domain.score.Score;

public abstract class Running implements State {

    @Override
    public final boolean isFinish() {
        return false;
    }

    @Override
    public final Score score() {
        return Score.unavailable();
    }
}
