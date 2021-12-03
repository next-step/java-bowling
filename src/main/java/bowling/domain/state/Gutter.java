package bowling.domain.state;

import bowling.domain.value.Pins;

import java.util.Objects;

public class Gutter implements State {
    private final Pins firstPins;
    private final Pins secondPins;

    private Gutter(Pins firstPin, Pins secondPins) {
        this.firstPins = firstPin;
        this.secondPins = secondPins;
    }

    public static State of(Pins firstPin) {
        return new Gutter(firstPin, null);
    }

    public static State of(Pins firstPin, Pins secondPins) {
        return new Gutter(firstPin, secondPins);
    }

    @Override
    public State pitch(Pins pins) {
        if (pins.isStrikeOrSpare()) {
            return Spare.of(firstPins, pins);
        }

        return Miss.of(firstPins, pins);
    }

    @Override
    public boolean isFinish() {
        return !Objects.isNull(secondPins);
    }

    @Override
    public String mark() {
        if (Objects.isNull(secondPins)) {
            return checkGutter(firstPins);
        }

        return checkGutter(firstPins) + DELIMITER + checkGutter(secondPins);
    }
}
