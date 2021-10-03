package bowling.domain.state;

import bowling.domain.PinCount;

public class StateFactory {
    public static State ready() {
        return new Ready();
    }

    public static State play(PinCount pinCount) {
        return ready().play(pinCount);
    }
}