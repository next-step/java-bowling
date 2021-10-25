package bowling.exception.state;

import bowling.exception.CustomException;

public class RunningCreateScoreException extends CustomException {

    private static final String RUNNING_CREATE_SCORE_ERROR_MESSAGE = "running 상태는 score를 반환할 수 없습니다.";

    public RunningCreateScoreException(String message) {
        super(message);
    }

    public RunningCreateScoreException() {
        this(RUNNING_CREATE_SCORE_ERROR_MESSAGE);
    }

}
