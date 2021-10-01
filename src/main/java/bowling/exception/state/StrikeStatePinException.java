package bowling.exception.state;

import bowling.exception.CustomException;

public class StrikeStatePinException extends CustomException {

    private static final String STRIKE_STATE_PIN_ERROR_MESSAGE ="Strike는 pin이 한번에 10개가 무너져야합니다.";

    public StrikeStatePinException(String message) {
        super(message);
    }

    public StrikeStatePinException() {
        this(STRIKE_STATE_PIN_ERROR_MESSAGE);
    }

}
