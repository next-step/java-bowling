package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;

public enum Strike implements BallState {

    INSTANCE;

    private static final int BONUS_COUNT = 2;

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public BallState state(Pins countOfHit) {
        throw new IllegalStateException(String.format("strike(%s) can not throw ball", this));
    }

    @Override
    public int bonusCount() {
        return BONUS_COUNT;
    }
}
