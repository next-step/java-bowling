package bowling.domain.state;

import bowling.domain.pins.Pins;

public class Miss implements Finished {

    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public boolean isClear() {
        return false;
    }

    @Override
    public String toString() {
        return firstPins + "|" + (secondPins.getPins() == 0 ? "-" : secondPins);
    }
}
