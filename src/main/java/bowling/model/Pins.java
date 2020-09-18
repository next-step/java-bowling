package bowling.model;

import bowling.ExceptionMessages;

import java.util.Objects;

public class Pins {
    public static final int MIN_PINS = 0;
    public static final int MAX_PINS = 10;

    private final int fallenPins;

    public Pins() {
        this(MAX_PINS);
    }

    private Pins(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    public static Pins of(int fallenPins) {
        validatePins(fallenPins);
        return new Pins(fallenPins);
    }

    private static void validatePins(int pins) {
        if (pins < MIN_PINS) {
            throw new IllegalArgumentException(ExceptionMessages.PINS_MIN_EXCEPTION);
        }

        if (pins > MAX_PINS) {
            throw new IllegalArgumentException(ExceptionMessages.PINS_MAX_EXCEPTION);
        }
    }

    private void verifyNextFallenPins(int nextFallenPins) {
        validatePins(nextFallenPins);
        if (this.fallenPins + nextFallenPins > MAX_PINS) {
            throw new IllegalArgumentException(ExceptionMessages.PINS_LAST_PINS_EXCEPTION);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return fallenPins == pins.fallenPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }

    public State getNextState(int nextFallenPins) {
        verifyNextFallenPins(nextFallenPins);
        if (fallenPins + nextFallenPins == MAX_PINS) {
            return State.getStateByPins(MAX_PINS, false);
        }

        return State.getStateByPins(nextFallenPins, false);
    }

    public State getState() {
        return State.getStateByPins(fallenPins, true);
    }

}
