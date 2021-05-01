package bowling.domain.state;

import bowling.domain.HitCount;
import bowling.exception.NoMoreBowlActionsException;

import static java.lang.Boolean.TRUE;

public abstract class Finish implements State {

    @Override
    public final boolean isFinish() {
        return TRUE;
    }

    @Override
    public final State bowl(final HitCount hitCount) {
        throw new NoMoreBowlActionsException();
    }

}
