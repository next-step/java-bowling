package bowling.domain;

import java.util.Objects;

public class PinNumber {
    public static final int MINIMUM_NUMBER = 0;
    public static final int MAXIMUM_NUMBER = 10;

    private final int number;

    public PinNumber(int number) {
        this.number = validatePinNumber(number);
    }

    private int validatePinNumber(int number) {
        if (number < MINIMUM_NUMBER || number > MAXIMUM_NUMBER) {
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
