package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;

public final class Strike implements BallState {

    private static final Strike INSTANCE = new Strike();
    private static final int BONUS_COUNT = 2;

    private static final Pins COUNT = Pins.MAX;

    private Strike() {
        if (INSTANCE != null) {
            throw new AssertionError();
        }
    }

    public static BallState instance() {
        return INSTANCE;
    }

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
