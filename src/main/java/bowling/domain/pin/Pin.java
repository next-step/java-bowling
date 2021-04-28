package bowling.domain.pin;

import bowling.exception.InvalidPinCountException;

import java.util.Objects;

public final class Pin {

    public static final String GUTTER_STATUS = "-";
    public static final int MIN_COUNT = 0;
    public static final int MAX_COUNT = 10;
    private static final int DEFAULT_PIN_COUNT = 10;

    private final int pin;

    public Pin() {
        this(DEFAULT_PIN_COUNT);
    }

    public Pin(final int pin) {
        validatePinCount(pin);
        this.pin = pin;
    }

    private void validatePinCount(int pin) {
        if (pin < MIN_COUNT || pin > MAX_COUNT) {
            throw new InvalidPinCountException();
        }
    }

    public int pinCount() {
        return pin;
    }

    public String status() {
        if (pin == MIN_COUNT) {
            return GUTTER_STATUS;
        }
        return String.valueOf(pin);
    }

    public Pin sum(Pin other) {
        return new Pin(pin + other.pin);
    }

    public boolean isMaximum() {
        return pin == MAX_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin1 = (Pin) o;
        return pin == pin1.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
