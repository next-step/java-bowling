package bowling.state.ready;

import bowling.state.State;
import bowling.state.finish.Strike;

public class First extends Ready {

    public static First of() {
        return new First();
    }

    @Override
    public State bowl(int felledPins) {
        if (felledPins == FELLED_ALL_PINS) {
            return Strike.of();
        }
        return Second.of(felledPins);
    }

    @Override
    public String view() {
        return STANDBY;
    }
}
