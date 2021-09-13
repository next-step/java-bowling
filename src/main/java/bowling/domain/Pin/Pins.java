package bowling.domain.Pin;

import bowling.exception.Pin.PinSecondValueException;

public class Pins {

    private static final int EMPTY_PIN_VALUE = 0;

    private final Pin first;
    private final Pin second;

    private Pins(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    private Pins(Pin first) {
        this(first, null);
    }

    public static Pins ofFirst(Pin pin) {
        return new Pins(pin);
    }

    public Pins ofSecond(Pin pin) {
        checkSecondPin(first, pin);

        return new Pins(this.first, pin);
    }

    private static void checkSecondPin(Pin first, Pin second) {
        if (!first.isRemainPin(second)) {
            throw new PinSecondValueException();
        }
    }

    public boolean isRemain() {
        return first.isRemainPin(Pin.of(EMPTY_PIN_VALUE));
    }

}
