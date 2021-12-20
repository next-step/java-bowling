package bowling.domain.state;

import bowling.domain.frame.Score;

public abstract class Running implements State {
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score score() {
        return Score.noScore();
    }
}
