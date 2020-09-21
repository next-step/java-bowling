package bowling.model;

import bowling.exception.PinsLastPinsException;
import bowling.exception.PinsMaxException;
import bowling.exception.PinsMinException;

import java.util.Objects;

public class Pins {
    public static final int MIN_PINS = 0;
    public static final int MAX_PINS = 10;

    private final int fallenPins;

    private Pins(int fallenPins) {
        this.fallenPins = fallenPins;
    }

    public static Pins of(int fallenPins) {
        validatePins(fallenPins);
        return new Pins(fallenPins);
    }

    private static void validatePins(int pins) {
        if (pins < MIN_PINS) {
            throw new PinsMinException();
        }

        if (pins > MAX_PINS) {
            throw new PinsMaxException();
        }
    }

    public State getState() {
        return State.getStateByPins(fallenPins, true);
    }

    private void verifyNextFallenPins(int nextFallenPins) {
        validatePins(nextFallenPins);
        if (this.fallenPins + nextFallenPins > MAX_PINS) {
            throw new PinsLastPinsException();
        }
    }

    private boolean areAllPinsFallen(int nextFallenPins) {
        return fallenPins + nextFallenPins == MAX_PINS;
    }

    public State getNextState(int nextFallenPins) {
        verifyNextFallenPins(nextFallenPins);
        if (areAllPinsFallen(nextFallenPins)) {
            return State.getStateByPins(MAX_PINS, false);
        }

        return State.getStateByPins(nextFallenPins, false);
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

}
