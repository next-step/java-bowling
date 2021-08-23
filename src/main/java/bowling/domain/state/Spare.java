package bowling.domain.state;

import bowling.domain.pins.Pins;

public class Spare implements Finished {

    private final Pins firstPins;
    private final Pins secondPins;

    private Spare(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPins, Pins secondPins) {
        return new Spare(firstPins, secondPins);
    }

    @Override
    public boolean isClear() {
        return true;
    }

    @Override
    public String toString() {
        return firstPins +"|/";
    }
}
