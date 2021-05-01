package bowling.domain.state;

import bowling.domain.HitCount;

import static java.lang.Boolean.TRUE;

public abstract class Finish implements State {

    @Override
    public boolean isFinish() {
        return TRUE;
    }

    @Override
    public final State bowl(final HitCount hitCount) {
        return null;
    }

}
