package bowling.domain.state;

import bowling.domain.Score;
import bowling.exception.NotSupportMethodException;

abstract class Ongoing implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public boolean hasBonusChance() {
        return false;
    }

    @Override
    public Score score() {
        throw new NotSupportMethodException();
    }
}
