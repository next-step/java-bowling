package bowling.domain.state;

import bowling.domain.pins.Pins;

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
        if (firstPins.isSpare(pins)) {
            return Spare.of(firstPins, pins);
        }
        if (firstPins.isMiss(pins)) {
            return Miss.of(firstPins, pins);
        }

        return Gutter.of();
    }
}
