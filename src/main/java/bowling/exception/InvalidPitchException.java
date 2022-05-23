package bowling.exception;

import bowling.domain.Pins;

public class InvalidPitchException extends RuntimeException {

    private static final String INVALID_PITCH_MESSAGE_FORMAT = "올바르지 못한 핀 입력입니다 : %s";

    public InvalidPitchException(Pins secondPins) {
        super(String.format(INVALID_PITCH_MESSAGE_FORMAT, secondPins));
    }

}
