package bowling.domain.state;

import bowling.domain.HitCount;

public abstract class Finish implements State {

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public State bowl(HitCount hitCount) {
        return null;
    }
}
