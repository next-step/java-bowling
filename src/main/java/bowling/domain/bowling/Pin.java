package bowling.domain.bowling;

import bowling.exception.bowling.PinSecondValueException;
import bowling.exception.bowling.PinValueException;

public class Pin {

    private static final int PIN_MIN_VALUE = 0;
    private static final int PIN_MAX_VALUE = 10;

    private final int first;
    private final int second;

    private Pin(int first) {
        this(first, PIN_MIN_VALUE);
    }

    private Pin(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public static Pin of(int pin) {
        checkPinValue(pin);

        return new Pin(pin);
    }

    public static Pin from(Pin input, int second) {
        checkPinValue(second);
        checkSecondPin(input, second);

        return new Pin(input.first, second);
    }

    private static void checkPinValue(int pin) {
        if (pin < PIN_MIN_VALUE || pin > PIN_MAX_VALUE) {
            throw new PinValueException();
        }
    }

    private static void checkSecondPin(Pin first, int second) {
        if (second > Math.subtractExact(PIN_MAX_VALUE, first.first)) {
            throw new PinSecondValueException();
        }
    }

}
