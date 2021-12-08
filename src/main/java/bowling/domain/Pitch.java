package bowling.domain;

import java.util.Objects;

public class Pitch {
    public static final int GUTTER_PIN_NUMBER = 0;
    public static final int SPARE_PIN_NUMBER = 10;
    public static final int STRIKE_PIN_NUMBER = 10;

    private final int pins;

    public Pitch(int pins) {
        this.pins = validatePinNumber(pins);
    }

    private int validatePinNumber(int number) {
        if (number < GUTTER_PIN_NUMBER || number > STRIKE_PIN_NUMBER) {
            throw new IllegalArgumentException("Pin number must be between 0 and 10");
        }

        return number;
    }

    public int getPins() {
        return pins;
    }

    public Score score() {
        return new Score(pins);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Pitch pin = (Pitch) object;

        return pins == pin.pins;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pins);
    }
}
