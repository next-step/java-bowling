package bowling.model;

import java.util.Objects;

public class Pin {

    public static final String INVALID_PIN_MESSAGE = "유효한 핀의 개수는 0~10개 입니다.";
    public static final int MIN_PIN = 0;
    public static final int MAX_PIN = 10;
    private final int pin;

    public Pin(int pin) {
        validate(pin);
        this.pin = pin;
    }

    private void validate(int pin) {
        if (!isValidPin(pin)) {
            throw new IllegalArgumentException(INVALID_PIN_MESSAGE);
        }
    }

    private boolean isValidPin(int pin) {
        return MIN_PIN <= pin && pin <= MAX_PIN;
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
