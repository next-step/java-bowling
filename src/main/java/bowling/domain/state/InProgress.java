package bowling.domain.state;

import bowling.domain.pin.DownedPins;

public class InProgress extends State {
    public static InProgress from(DownedPins downedPins) {
        return new InProgress();
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        return null;
    }
}
