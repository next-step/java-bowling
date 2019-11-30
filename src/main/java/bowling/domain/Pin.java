package bowling.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pin {
    public static final int ALL_PIN_COUNT = 10;
    public static final int DEFAULT_PIN = -1;
    private static final String PIN_SIZE_ERROR_MESSAGE = "Ball은 10개 이하의 pin만 갖을 수 있습니다.";
    private static final Map<Integer, Pin> cache;
    private int pin;

    static {
        cache = new HashMap<>();
        for (int i = DEFAULT_PIN; i <= ALL_PIN_COUNT; i++) {
            cache.put(i, new Pin(i));
        }
    }

    private Pin(int pin) {
        this.pin = pin;
    }

    public static Pin of(int pin) {
        return cache.get(pin);
    }

    public static Pin ofDefault() {
        return cache.get(DEFAULT_PIN);
    }

    public static Pin ofAllPin() {
        return cache.get(ALL_PIN_COUNT);
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

    public int sum(int other) {
        return pin + other;
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
