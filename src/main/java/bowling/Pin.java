package bowling;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Pin {

    public final static int MAX_PINS = 10;
    public final static int MIN_PINS = 0;
    private final static Map<Integer, Pin> INSTANCES = new HashMap<>();
    private final int pin;

    static {
        IntStream.rangeClosed(MIN_PINS, MAX_PINS)
                .forEach(pin -> INSTANCES.put(pin, new Pin(pin)));
    }

    private Pin(int pin) {
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    private static void validate(int pin) throws IllegalArgumentException {
        if (pin < MIN_PINS || pin > MAX_PINS) {
            throw new IllegalArgumentException("입력된 핀 갯수가 범위를 초과했습니다.");
        }
    }

    public static Pin ofMin() {
        return INSTANCES.get(MIN_PINS);
    }

    public static Pin ofMax() {
        return INSTANCES.get(MAX_PINS);
    }

    public static Pin of(int pin) {
        validate(pin);
        return INSTANCES.get(pin);
    }

    public static Pin of(Pin pin) {
        validate(pin.pin);
        return INSTANCES.get(pin.pin);
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
        return pin;
    }
}
