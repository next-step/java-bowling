package bowling.domain;

import java.util.Objects;

public class PinNumber {
    public static final int GUTTER_PIN_NUMBER = 0;
    public static final int SPARE_PIN_NUMBER = 10;

    public static final int STRIKE_PIN_NUMBER = 10;

    private final int number;

    public PinNumber(int number) {
        this.number = validatePinNumber(number);
    }

    public int getNumber() {
        return number;
    }

    private int validatePinNumber(int number) {
        if (number < GUTTER_PIN_NUMBER || number > STRIKE_PIN_NUMBER) {
            throw new IllegalArgumentException("Pin number must be between 0 and 10");
        }

        return number;
    }

    public PinNumber add(PinNumber other) {
        return new PinNumber(this.number + other.number);
    }

    public Score score() {
        return new Score(number);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        PinNumber pinNumber = (PinNumber) object;

        return number == pinNumber.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
