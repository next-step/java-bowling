package bowling.domain;

import bowling.exception.PinValueRangeException;

public class Pin {

    private static final int MIN_PIN = 0;
    private static final int MAX_PIN = 10;
    private static final String PIN_VALUE_EXCEPTION_MESSAGE = "핀은 0 ~ 10 사이의 값만 가질 수 있습니다";

    private final int pin;

    public Pin(int pin) {
        this.pin = pin;
        validatePin();
    }

    private void validatePin() {
        if (pin < MIN_PIN || pin > MAX_PIN) {
            throw new PinValueRangeException(PIN_VALUE_EXCEPTION_MESSAGE);
        }
    }

    public int count() {
        return pin;
    }
}

