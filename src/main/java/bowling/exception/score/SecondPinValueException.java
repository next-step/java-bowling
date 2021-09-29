package bowling.exception.score;

import bowling.exception.CustomException;

public class SecondPinValueException extends CustomException {

    private static final String SECOND_PIN_SAVE_ERROR_MESSAGE = "두번째 핀은 남은 핀만 저장할 수 있습니다.";

    public SecondPinValueException(String message) {
        super(message);
    }

    public SecondPinValueException() {
        this(SECOND_PIN_SAVE_ERROR_MESSAGE);
    }

}
