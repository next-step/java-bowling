package bowling.exception.state;

import bowling.exception.CustomException;

public class StateCannotCalculateScoreException extends CustomException {

    private static final String CALCULATE_SCORE_ERROR_MESSAGE = "현재 Running상태는 더이상 Score를 계산할 수 없습니다.";

    public StateCannotCalculateScoreException(String message) {
        super(message);
    }

    public StateCannotCalculateScoreException() {
        this(CALCULATE_SCORE_ERROR_MESSAGE);
    }

}
