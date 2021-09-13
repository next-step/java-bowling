package bowling.exception.Pin;

import bowling.exception.CustomException;

public class PinValueException extends CustomException {

    private static final String INVALID_PIN_VALUE_ERROR_MESSAGE = "핀은 0~10 사이의 값만 저장할 수 있다.";

    public PinValueException(String message) {
        super(message);
    }

    public PinValueException() {
        this(INVALID_PIN_VALUE_ERROR_MESSAGE);
    }

}
