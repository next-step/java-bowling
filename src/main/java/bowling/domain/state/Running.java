package bowling.domain.state;

import bowling.domain.Score;
import bowling.domain.State;

abstract class Running implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new UnsupportedOperationException();
    }
}