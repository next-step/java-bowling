package bowling.domain.state.running;

import bowling.domain.score.Score;
import bowling.domain.state.State;

public abstract class Running implements State {

    @Override
    public boolean isFrameEnd() {
        return false;
    }

    @Override
    public Score score() {
        return Score.unavailable();
    }

}
