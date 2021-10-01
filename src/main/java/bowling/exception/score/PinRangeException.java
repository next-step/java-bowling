package bowling.exception.score;

import bowling.exception.CustomException;

public class PinRangeException extends CustomException {

    private static final String PIN_RANGE_ERROR_MESSAGE = "Pin은 0 ~ 10 사이의 수만 저장될 수 있습니다.";

    public PinRangeException(String message) {
        super(message);
    }

    public PinRangeException() {
        this(PIN_RANGE_ERROR_MESSAGE);
    }

}
