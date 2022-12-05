package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.DoNotHaveEnoughPointsException;

public abstract class Running implements State {

    @Override
    public Score score() {
        throw new DoNotHaveEnoughPointsException();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
