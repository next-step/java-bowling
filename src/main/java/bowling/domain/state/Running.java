package bowling.domain.state;

import bowling.domain.Score;

public abstract class Running implements State {

    @Override
    public Score score() {
        throw new IllegalStateException("can't get score while running");
    }

    @Override
    public boolean finished() {
        return false;
    }
}
