package bowling.exception.state;

import bowling.exception.CustomException;

public class SpareStateFirstPinStrikeException extends CustomException {

    private static final String SPARE_STATE_FIRST_PIN_STRIKE_ERROR_MESSAGE = "첫 핀이 스트라이크라면 Spare상태가 될 수 없습니다.";

    public SpareStateFirstPinStrikeException(String message) {
        super(message);
    }

    public SpareStateFirstPinStrikeException() {
        this(SPARE_STATE_FIRST_PIN_STRIKE_ERROR_MESSAGE);
    }

}
