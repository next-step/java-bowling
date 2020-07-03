package bowling.step3.domain.state;

import bowling.step3.domain.score.Score;

public abstract class Running implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        return Score.readyScore();
    }
}
