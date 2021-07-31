package bowling.domain.state;

import bowling.domain.pin.DownedPins;

public class Spare extends State {
    public static State from(DownedPins downedPins) {
        return null;
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        return null;
    }
}
