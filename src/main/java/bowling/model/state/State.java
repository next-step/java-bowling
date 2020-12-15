package bowling.model.state;

import bowling.model.Score;

public abstract class State {
    protected Score score = Score.min();

    public boolean isFinished() {
        return false;
    }

    public abstract State bowling(int fallenPin);

    public boolean isMaxScore() {
        return score.isMaxScore();
    }
}
