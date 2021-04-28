package step4.domain.state;

import step4.domain.Score;

public abstract class Running implements State {
    @Override
    public Score score() {
        return Score.unCountableScore();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
