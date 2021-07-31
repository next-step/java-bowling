package bowling.domain.state;

import bowling.domain.pin.DownedPin;

public class Strike extends State {
    public static Object instance() {
        return null;
    }

    @Override
    protected State nextState(DownedPin downedPin) {
        return null;
    }
}
