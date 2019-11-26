package bowling.domain;

import java.util.Objects;

public class Pin {
    public static final int ZERO_PIN_COUNT = 0;
    public static final int ALL_PIN_COUNT = 10;
    public static final int DEFAULT_PIN = -1;
    private static final String PIN_SIZE_ERROR_MESSAGE = "Ball은 10개 이하의 pin만 갖을 수 있습니다.";
    private int pin;

    public Pin(int pin) {
        this.pin = pin;
    }

    public Pin() {
        this.pin = DEFAULT_PIN;
    }

    public void fallDown(int pin) {
        if (pin > ALL_PIN_COUNT) {
            throw new IllegalArgumentException(PIN_SIZE_ERROR_MESSAGE);
        }

        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public boolean isNotFallDown() {
        return this.pin == DEFAULT_PIN;
    }

    public boolean isStrike() {
        return pin == ALL_PIN_COUNT;
    }

    public boolean isZero() {
        return pin == ALL_PIN_COUNT;
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
