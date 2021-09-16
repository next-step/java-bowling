package bowling.exception.score;

import bowling.exception.CustomException;

public class PinSecondValueException extends CustomException {

    private static final String INVALID_SECOND_PIN_ERROR_MESSAGE = "첫번째 핀이 쓰러뜨리고 남은 핀 개수만 저장할 수 있다.";

    public PinSecondValueException(String message) {
        super(message);
    }

    public PinSecondValueException() {
        this(INVALID_SECOND_PIN_ERROR_MESSAGE);
    }

}
