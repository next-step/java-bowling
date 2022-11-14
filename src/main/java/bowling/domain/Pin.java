package bowling.domain;

import bowling.exception.BowlingGameException;
import bowling.exception.ErrorMessage;

import java.util.Objects;

public class Pin {
    public static final int MINIMUM_PIN_COUNT = 0;
    public static final int MAXIMUM_PIN_COUNT = 10;
    private final int count;

    public Pin(int count) {
        validateOutOfRange(count);
        this.count = count;
    }

    private void validateOutOfRange(int count) {
        if (count < MINIMUM_PIN_COUNT || count > MAXIMUM_PIN_COUNT) {
            throw new BowlingGameException(ErrorMessage.NUMBER_OF_PINS_OUT_OF_RANGE);
        }
    }

    public Score toScore() {
        return new Score(count);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return count == pin.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }

    public int getScore() {
        return this.count;
    }

    @Override
    public String toString() {
        return "Pin{" +
                "count=" + count +
                '}';
    }
}
