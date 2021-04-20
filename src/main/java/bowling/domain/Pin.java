package bowling.domain;

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
        if (pin < BOWLING_PIN_MIN_SIZE || pin > BOWLING_PIN_MAX_SIZE) {
            throw new PinOutOfSizeException();
        }
        return new Pin(pin);
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
}
