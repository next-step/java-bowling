package bowling.domain.pin;

import bowling.exception.pin.PinRangeException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Pin {

    private static final int PIN_MIN_VALUE = 0;
    private static final int PIN_MAX_VALUE = 10;

    private static final Map<Integer, Pin> pins = new HashMap<>();

    private final int pin;

    {
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

}
