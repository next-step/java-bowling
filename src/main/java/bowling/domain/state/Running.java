package bowling.domain.state;

import bowling.domain.HitCount;

public abstract class Running implements State {

    @Override
    public boolean isFinish() {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }

}
