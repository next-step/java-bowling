package bowling.domain.Pin;

import bowling.exception.Pin.PinSecondValueException;

public class FinalPins {

    private final Pin first;
    private final Pin second;
    private final Pin bonus;

    private FinalPins(Pin first, Pin second, Pin bonus) {
        this.first = first;
        this.second = second;
        this.bonus = bonus;
    }

    public static FinalPins ofFirst(Pin pin) {
        return new FinalPins(pin, null, null);
    }

    public FinalPins ofNext(Pin pin) {
        if (second == null) {
            checkSecondPin(first, pin);
            return new FinalPins(first, pin, null);
        }
        return new FinalPins(first, second, pin);
    }

    private static void checkSecondPin(Pin first, Pin second) {
        if (!first.isRemainPin(second)) {
            throw new PinSecondValueException();
        }
    }

}
