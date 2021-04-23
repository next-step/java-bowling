package bowling.domain.pin;

import bowling.exception.PinOutOfSizeException;

import java.util.Objects;

public class Pin {

    public static final int BOWLING_PIN_MAX_SIZE = 10;
    public static final int BOWLING_PIN_MIN_SIZE = 0;
    private final int value;

    private Pin(final int value) {
        this.value = value;
    }

    public static Pin of(final int pin) {
        if (isLessThanMinSize(pin) || isLargerThanMaxSize(pin)) {
            throw new PinOutOfSizeException();
        }
        return new Pin(pin);
    }

    private static boolean isLargerThanMaxSize(final int pin) {
        return pin > BOWLING_PIN_MAX_SIZE;
    }

    private static boolean isLessThanMinSize(final int pin) {
        return pin < BOWLING_PIN_MIN_SIZE;
    }

    public boolean isOutOfSize(final int secondPin) {
        return value + secondPin > BOWLING_PIN_MAX_SIZE;
    }

    public boolean isLessThanMaxSize() {
        return value < BOWLING_PIN_MAX_SIZE;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Pin)) return false;
        final Pin pin = (Pin) o;
        return value == pin.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
