package bowling.domain.state;

import bowling.domain.pin.DownedPins;

public class Spare extends State {
    private final DownedPins downedPins;

    private Spare(DownedPins downedPins) {
        this.downedPins = downedPins;
    }


    public static State from(DownedPins downedPins) {
        return new Spare(downedPins);
    }

    @Override
    protected State nextState(DownedPins downedPins) {
        return null;
    }
}
