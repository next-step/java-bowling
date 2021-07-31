package bowling.domain.state;

import bowling.domain.pin.DownedPins;

public class Miss extends State {
    @Override
    protected State nextState(DownedPins downedPins) {
        return null;
    }
}
