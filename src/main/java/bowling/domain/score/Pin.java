package bowling.domain.score;

import bowling.exception.pin.PinRangeException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

public class Pin {

    private static final int PIN_MIN_VALUE = 0;
    private static final int PIN_MAX_VALUE = 10;

    private static final Map<Integer, Pin> pins = new HashMap<>();

    private final int pin;

    static {
        IntStream.rangeClosed(PIN_MIN_VALUE, PIN_MAX_VALUE)
            .forEach(number -> pins.put(number, new Pin(number)));
    }

    private Pin(int pin) {
        this.pin = pin;
    }

    public static Pin of(int pin) {
        checkPinRange(pin);

        return pins.get(pin);
    }

    private static void checkPinRange(int pin) {
        if (!pins.containsKey(pin)) {
            throw new PinRangeException();
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
