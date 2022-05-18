package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;

public enum NotThrown implements BallState {

    INSTANCE;

    private static final int REMAIN_COUNT = 2;

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public BallState state(Pins countOfHit) {
        if (Pins.MAX.equals(countOfHit)) {
            return Strike.INSTANCE;
        }
        return FirstThrown.from(countOfHit);
    }

    @Override
    public int remainCount() {
        return REMAIN_COUNT;
    }

    @Override
    public String mark() {
        return "";
    }

    @Override
    public int sumPinsCount() {
        return 0;
    }
}
