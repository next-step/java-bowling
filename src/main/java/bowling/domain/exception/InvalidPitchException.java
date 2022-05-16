package bowling.domain.exception;

import bowling.domain.Pins;

public class InvalidPitchException extends RuntimeException {
    public InvalidPitchException(Pins secondPins) {
        super(String.format("모든 핀을 초과하였습니다. : %s", secondPins));
    }

}
