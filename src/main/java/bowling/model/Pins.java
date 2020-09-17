package bowling.model;

import bowling.ExceptionMessages;

import java.util.Objects;

public class Pins {
    public static final int MIN_PINS = 0;
    public static final int MAX_PINS = 10;

    private final int countOfPins;

    public Pins() {
        countOfPins = MAX_PINS;
    }

    public Pins(int countOfPins) {
        validatePins(countOfPins);
        this.countOfPins = countOfPins;
    }

    public static Pins remainPins(int fallenPins) {
        return new Pins(MAX_PINS - fallenPins);
    }

    public Pins fallingPins(int fallenPins) {
        validateLastPins(fallenPins);

        return new Pins(countOfPins - fallenPins);
    }

    public boolean areAllPinsFallen() {
        return this.countOfPins == MIN_PINS;
    }

    private void validatePins(int pins) {
        if (pins < MIN_PINS) {
            throw new IllegalArgumentException(ExceptionMessages.PINS_MIN_EXCEPTION);
        }

        if (pins > MAX_PINS) {
            throw new IllegalArgumentException(ExceptionMessages.PINS_MAX_EXCEPTION);
        }
    }

    private void validateLastPins(int fallenPins) {
        if (countOfPins - fallenPins < MIN_PINS) {
            throw new IllegalArgumentException(ExceptionMessages.PINS_LAST_PINS_EXCEPTION);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pins pins = (Pins) o;
        return countOfPins == pins.countOfPins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(countOfPins);
    }

}
