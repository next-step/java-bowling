package bowling.domain.state;

import bowling.domain.pin.PinCount;
import bowling.domain.state.running.Ready;

public class StateFactory {

    public static State ready() {
        return Ready.of();
    }

    public static State hit(final PinCount count) {
        return ready().bowl(count);
    }
}
