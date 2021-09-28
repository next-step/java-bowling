package bowling.domain.pin;

import bowling.exception.pin.PinRangeException;

public class Pin {

    private final int pin;

    private Pin(int pin) {
        this.pin = pin;
    }

    public static Pin of(int pin) {
        checkPinRange(pin);

        return new Pin(pin);
    }

    private static void checkPinRange(int pin) {
        if (pin < 0 || pin > 10) {
            throw new PinRangeException();
        }
    }

}
