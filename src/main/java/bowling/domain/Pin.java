package bowling.domain;

import java.util.Objects;

public class Pin {

    private static final int MIN_PIN = 0;
    private static final int MAX_PIN = 10;
    private static final String PIN_VALUE_EXCEPTION_MESSAGE = "핀은 0 ~ 10 사이의 값만 가질 수 있습니다";

    private final int pin;

    public Pin(int pin) {
        this.pin = pin;
        validatePin();
    }

    private void validatePin() {
        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new IllegalArgumentException(PIN_VALUE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pin pin1 = (Pin) o;
        return pin == pin1.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}

