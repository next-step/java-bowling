package bowling.domain.state;

import bowling.domain.value.Pins;

public class Miss implements State {
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
    public State pitch(Pins pins) {
        throw new IllegalArgumentException(INVALID_PITCH);
    }

    @Override
    public Score calculateScore() {
        return Score.ofMiss(firstPins.sum(secondPins));
    }

    @Override
    public String mark() {
        return checkGutter(firstPins) + DELIMITER + checkGutter(secondPins);
    }
}
