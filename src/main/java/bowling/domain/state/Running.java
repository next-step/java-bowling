package bowling.domain.state;

import bowling.domain.Score;

public abstract class Running implements State {
    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        return null;
    }

}
