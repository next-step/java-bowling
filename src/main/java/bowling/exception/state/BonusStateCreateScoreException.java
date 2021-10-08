package bowling.exception.state;

import bowling.exception.CustomException;

public class BonusStateCreateScoreException extends CustomException {

    private static final String BONUS_STATE_CREATE_ERROR_MESSAGE = "Bonus state는 Score를 생성할 수 없습니다.";

    public BonusStateCreateScoreException(String message) {
        super(message);
    }

    public BonusStateCreateScoreException() {
        this(BONUS_STATE_CREATE_ERROR_MESSAGE);
    }

}
