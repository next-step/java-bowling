package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.CannotCalculateException;

abstract class Running implements State {
    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public Score getScore() {
        throw new CannotCalculateException();
    }
}
