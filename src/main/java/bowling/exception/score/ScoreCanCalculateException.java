package bowling.exception.score;

import bowling.exception.CustomException;

public class ScoreCanCalculateException extends CustomException {

    private static final String SCORE_CALCULATE_ERROR_MESSAGE = "현재 score는 남은 보너스 횟수가 있어 반환할 수 없습니다.";

    public ScoreCanCalculateException(String message) {
        super(message);
    }

    public ScoreCanCalculateException() {
        this(SCORE_CALCULATE_ERROR_MESSAGE);
    }

}
