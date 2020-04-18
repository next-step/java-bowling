package seul.bowling.domain;

import lombok.Getter;
import seul.bowling.exception.BowlingException;
import seul.bowling.exception.ExceptionType;

public class Pin {
    private static final int MAX_PIN_COUNT = 10;

    @Getter
    private int pin;

    public Pin(int pin) {
        if (pin > MAX_PIN_COUNT) {
            throw new BowlingException(ExceptionType.INVALID_CLEAR_PIN_COUNT);
        }

        this.pin = pin;
    }
}
