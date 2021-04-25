package bowling.domain;

import java.util.Objects;

public class Pin {
    public static final int MAX_PIN_COUNT = 10;
    public static final int MIN_PIN_COUNT = 0;

    private int pin;

    private Pin(final int countOfDownPin) {
        this.pin = countOfDownPin;
    }

    public static Pin from(final int countOfDownPin) {
        if (countOfDownPin > MAX_PIN_COUNT) {
            throw new IllegalArgumentException("핀은 최대 10개까지 쓰러뜨릴 수 있습니다.");
        }
        if (countOfDownPin < MIN_PIN_COUNT) {
            throw new IllegalArgumentException("핀은 최소 0개까지 쓰러뜨릴 수 있습니다.");
        }
        return new Pin(countOfDownPin);
    }

    public int getPin() {
        return pin;
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
