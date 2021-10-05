package bowling.exception.state;

import bowling.exception.CustomException;

public class ReadyStateCalculateScoreException extends CustomException {

    private static final String READY_CALCULATE_SCORE_ERROR_MESSAGE = "Ready상태는 Score를 계산할 수 없습니다.";

    public ReadyStateCalculateScoreException(String message) {
        super(message);
    }

    public ReadyStateCalculateScoreException() {
        this(READY_CALCULATE_SCORE_ERROR_MESSAGE);
    }

}
