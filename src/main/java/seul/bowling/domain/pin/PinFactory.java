package seul.bowling.domain.pin;

import java.util.HashMap;
import java.util.Map;

public class PinFactory {
    private static final Map<Integer, Pin> PIN_POOL = new HashMap<>();

    private PinFactory() {
    }

    public static Pin getPin(int pinNumber) {
        if (!PIN_POOL.containsKey(pinNumber)) {
            PIN_POOL.put(pinNumber, Pin.of(pinNumber));
        }

        return PIN_POOL.get(pinNumber);
    }
}
