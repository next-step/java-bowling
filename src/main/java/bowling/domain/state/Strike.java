package bowling.domain.state;

import bowling.domain.pin.DownedPins;

public class Strike extends State {
    public static Strike instance() {
        return new Strike();
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        return null;
    }
}
