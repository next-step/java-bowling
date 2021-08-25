package bowling.domain.state;

import bowling.domain.pins.Pins;

import static bowling.domain.pins.Pins.MAX_PIN_NUMBER;
import static bowling.domain.pins.Pins.MIN_PIN_NUMBER;

public class FirstBowl implements Playing {

    private final Pins firstPins;

    private FirstBowl(Pins firstPins) {
        this.firstPins = firstPins;
    }

    public static State of(Pins firstPins) {
        return new FirstBowl(firstPins);
    }

    @Override
    public State bowl(Pins pins) {
        if (isSpare(pins)) {
            return Spare.of(firstPins, pins);
        }
        if (isMiss(pins)) {
            return Miss.of(firstPins, pins);
        }

        return Gutter.of();
    }

    private boolean isSpare(Pins pins) {
        return firstPins.getTotalPins(pins) == MAX_PIN_NUMBER;
    }

    private boolean isMiss(Pins pins) {
        int totalPins = firstPins.getTotalPins(pins);
        return totalPins > MIN_PIN_NUMBER && totalPins < MAX_PIN_NUMBER;
    }

    @Override
    public String toString() {
        return firstPins.toString();
    }
}
