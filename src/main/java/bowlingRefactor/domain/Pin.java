package bowlingRefactor.domain;

import java.util.Objects;

public class Pin {

    public static final int MAX_PIN = 10;
    public static final int DEFAULT_PIN = -1;
    public static final int MIN_PIN = 0;
    private static final String PIN_VALIDATION_MESSAGE = "핀의 개수가 올바르지 않습니다.";
    private int pin;

    public Pin() {
        this.pin = DEFAULT_PIN;
    }

    private Pin(int pinCount) {
        this.pin = pinCount;
    }

    public static Pin of(int pinCount) {
        return new Pin(pinCount);
    }

    public static Pin ofStart() {
        return new Pin(DEFAULT_PIN);
    }

    public void fallDown(int pin) {
        if (pin > MAX_PIN) {
            throw new IllegalArgumentException(PIN_VALIDATION_MESSAGE);
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
        return pin == MAX_PIN;
    }

    public boolean isZero() {
        return pin == MIN_PIN;
    }

    public int sum(int other) {
        return pin + other;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pin pin = (Pin) o;
        return this.pin == pin.pin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pin);
    }
}
