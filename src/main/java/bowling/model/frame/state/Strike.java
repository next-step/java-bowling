package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;

public enum Strike implements BallState {

    INSTANCE;

    private static final Pins HIT_PINS = Pins.MAX;
    private static final int REMAIN_COUNT = 2;

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public BallState state(Pins countOfHit) {
        throw new IllegalStateException(String.format("strike(%s) can not throw ball", this));
    }

    @Override
    public int remainCount() {
        return REMAIN_COUNT;
    }

    @Override
    public int sumPinsCount() {
        return HIT_PINS.count();
    }

    @Override
    public String mark() {
        return CountToMarkConverter.convert(HIT_PINS.count());
    }
}
