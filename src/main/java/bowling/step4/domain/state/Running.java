package bowling.step4.domain.state;

import bowling.step4.domain.score.Score;

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
