package bowling.exception.state;

import bowling.exception.CustomException;

public class RunningStateCalculateScoreException extends CustomException {

    private static final String RUNNING_CALCULATE_SCORE_ERROR_MESSAGE = "현재 Running상태는 더이상 Score를 계산할 수 없습니다.";

    public RunningStateCalculateScoreException(String message) {
        super(message);
    }

    public RunningStateCalculateScoreException() {
        this(RUNNING_CALCULATE_SCORE_ERROR_MESSAGE);
    }

}
