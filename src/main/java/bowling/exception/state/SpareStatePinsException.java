package bowling.exception.state;

import bowling.exception.CustomException;

public class SpareStatePinsException extends CustomException {

    private static final String SPARE_STATE_PINS_ERROR_MESSAGE = "두 핀의 합이 10이 되지 않으면 Spare상태가 될 수 없습니다.";

    public SpareStatePinsException(String message) {
        super(message);
    }

    public SpareStatePinsException() {
        this(SPARE_STATE_PINS_ERROR_MESSAGE);
    }

}
