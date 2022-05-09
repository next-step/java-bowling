package bowling.model.frame.state;

import bowling.model.Pins;
import bowling.model.frame.BallState;

public final class NotThrown implements BallState {

    private static final NotThrown INSTANCE = new NotThrown();

    private NotThrown() {
        if (INSTANCE != null) {
            throw new AssertionError();
        }
    }

    public static NotThrown instance() {
        return INSTANCE;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public BallState state(Pins countOfHit) {
        if (Pins.MAX.equals(countOfHit)) {
            return Strike.instance();
        }
        return FirstThrown.from(countOfHit);
    }
}
