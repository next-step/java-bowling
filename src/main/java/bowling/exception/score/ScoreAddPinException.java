package bowling.exception.score;

import bowling.exception.CustomException;

public class ScoreAddPinException extends CustomException {

    private static final String SCORE_ADD_PIN_ERROR_MESSAGE = "현재 score는 남은 보너스 횟수가 없어 점수를 더할 수 없습니다.";

    public ScoreAddPinException(String message) {
        super(message);
    }

    public ScoreAddPinException() {
        this(SCORE_ADD_PIN_ERROR_MESSAGE);
    }

}
