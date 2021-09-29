package bowling.exception.score;

import bowling.exception.CustomException;

public class BonusPinValueException extends CustomException {

    private static final String PIN_BONUS_ERROE_MESSAGE = "보너스 핀은 스페어나 스트라이크 후 칠 수 있습니다.";

    public BonusPinValueException(String message) {
        super(message);
    }

    public BonusPinValueException() {
        this(PIN_BONUS_ERROE_MESSAGE);
    }

}
