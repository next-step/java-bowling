package bowling.entity;

import java.util.Objects;

public class Pin {
    private final int pin;
    private final int MAX_PIN_COUNT = 10;
    private final int MIN_PIN_COUNT = 0;

    public Pin(int pin) {
        validatePin(pin);
        this.pin = pin;
    }

    private void validatePin(int pin) {
        if (pin > MAX_PIN_COUNT || pin < MIN_PIN_COUNT) {
            throw new IllegalArgumentException("쓰러트릴 수 있는 핀의 범위가 아닙니다.");
        }
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
