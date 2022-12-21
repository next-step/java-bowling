package bowling.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Pin {

    public static final String INVALID_PIN_MESSAGE = "유효한 핀의 개수는 0~10개 입니다.";
    public static final int MIN_PIN = 0;
    public static final int MAX_PIN = 10;
    private static final Map<Integer, Pin> pins = new HashMap<>();

    static {
        for (int i = MIN_PIN; i <= MAX_PIN; i++) {
            pins.put(i, new Pin(i));
        }
    }

    private final int pin;

    private Pin(int pin) {
        validate(pin);
        this.pin = pin;
    }

    private void validate(int pin) {
        if (!isValidPin(pin)) {
            throw new IllegalArgumentException(INVALID_PIN_MESSAGE);
        }
    }

    public static Pin of(int pin) {
        return Optional.ofNullable(pins.get(pin))
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PIN_MESSAGE));
    }

    public boolean isClearAll() {
        return pin == MAX_PIN;
    }

    public boolean isClearAll(Pin secondPin) {
        int totalPins = this.pin + secondPin.pin;
        validate(totalPins);
        return totalPins == MAX_PIN;
    }

    private boolean isValidPin(int pin) {
        return MIN_PIN <= pin && pin <= MAX_PIN;
    }

    public int getValue() {
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
