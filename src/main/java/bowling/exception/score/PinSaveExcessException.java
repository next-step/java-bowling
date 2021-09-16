package bowling.exception.score;

import bowling.exception.CustomException;

public class PinSaveExcessException extends CustomException {

    private static final String PIN_SAVE_EXCESS_ERROR_MESSAGE = "현재 Score가 다 차있을 때 더이상 Pin을 저장할 수 없다.";

    public PinSaveExcessException(String message) {
        super(message);
    }

    public PinSaveExcessException() {
        this(PIN_SAVE_EXCESS_ERROR_MESSAGE);
    }

}
