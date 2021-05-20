package bowling.domain.pin;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

public class Pin {
    private static final int MINIMUM_PIN = 0;
    private static final int MAXIMUM_PIN = 10;

    private int pin;

    public Pin(int pin) {
        if (!valid(MINIMUM_PIN, pin)) {
            throw new CustomException(ErrorCode.INVALID_PIN);
        }
        this.pin = pin;
    }

    public Pin(Pin firstPin, int pin) {
        if (!valid(firstPin.pin, pin)) {
            throw new CustomException(ErrorCode.INVALID_SECOND_PIN);
        }
        this.pin = pin;
    }

    private boolean valid(int prePin, int pin) {
        return pin >= MINIMUM_PIN && pin <= MAXIMUM_PIN && prePin + pin <= MAXIMUM_PIN;
    }

    public boolean didClear() {
        return pin == MAXIMUM_PIN;
    }

    public boolean didClear(Pin firstPin) {
        return firstPin.pin + pin == MAXIMUM_PIN;
    }

    public boolean guttered() {
        return pin == MINIMUM_PIN;
    }

    public int pin() {
        return pin;
    }
}
