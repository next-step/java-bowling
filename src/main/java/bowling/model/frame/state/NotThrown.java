package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;

public enum NotThrown implements BallState {

    INSTANCE;

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
}
