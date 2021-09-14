package bowling.exception.Pin;

import bowling.exception.CustomException;

public class PinBonusException extends CustomException {

    private static final String INVALID_PIN_BONUS_ERROE_MESSAGE = "보너스 핀은 스페어나 스트라이크 후 칠 수 있다.";

    public PinBonusException(String message) {
        super(message);
    }

    public PinBonusException() {
        this(INVALID_PIN_BONUS_ERROE_MESSAGE);
    }

}
