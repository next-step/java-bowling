package domain.pin;

import java.util.HashMap;
import java.util.Map;

public class Pin {
    public static final int MAXIMUM_PINS = 10;
    public static final int MINIMUM_PINS = 0;

    private final int pin;

    private static final Map<Integer, Pin> pins = new HashMap<>();

    private Pin(int pin) {
        if( pin > MAXIMUM_PINS ) {
            throw new IllegalArgumentException("핀의 숫자는 " + MAXIMUM_PINS + " 이하입니다.");
        }

        if( pin < MINIMUM_PINS ) {
            throw new IllegalArgumentException("핀의 숫자는" + MINIMUM_PINS + " 이상입니다.");
        }

        this.pin = pin;
    }

    public static Pin ofStrike() {
        return Pin.of(MAXIMUM_PINS);
    }

    public static Pin ofZero() {
        return Pin.of(MINIMUM_PINS);
    }

    private Pin add(Pin pin) {
        return Pin.of(this.pin + pin.getPin());
    }

    public static Pin of(int pin) {
        if(!pins.containsKey(pin)) {
            pins.put(pin, new Pin(pin));
        }

        return pins.get(pin);
    }

    public int getPin() {
        return pin;
    }

    public boolean isZeroPin() {
        return pin == MINIMUM_PINS;
    }

    public boolean isSpare(Pin pin) {
        return add(pin).getPin() == MAXIMUM_PINS;
    }

    public boolean isStrike() {
        return pin == MAXIMUM_PINS;
    }

    @Override
    public String toString() {
        return "" + pin;
    }
}