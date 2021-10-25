package bowling.exception.state;

import bowling.exception.CustomException;

public class MissStateCrerateException extends CustomException {

    public static final String MISS_STATE_CREATE_ERROR_MESSAGE = "Miss 상태는 두 핀의 합이 10이 넘을 수 없다.";

    public MissStateCrerateException(String message) {
        super(message);
    }

    public MissStateCrerateException() {
        this(MISS_STATE_CREATE_ERROR_MESSAGE);
    }

}
