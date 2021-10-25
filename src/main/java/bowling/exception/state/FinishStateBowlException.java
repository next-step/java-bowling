package bowling.exception.state;

import bowling.exception.CustomException;

public class FinishStateBowlException extends CustomException {

    private static final String FINISH_STATE_BOWL_ERROR_EXCEPTION = "종료된 상태에서 더이상 볼링을 던질 수 없습니다.";

    public FinishStateBowlException(String message) {
        super(message);
    }

    public FinishStateBowlException() {
        this(FINISH_STATE_BOWL_ERROR_EXCEPTION);
    }

}
