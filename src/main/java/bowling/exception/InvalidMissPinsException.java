package bowling.exception;

import bowling.domain.Pins;

public class InvalidMissPinsException extends RuntimeException {

    private static final String INVALID_MISS_PINS_MESSAGE_FORMAT = "유효하지 않는 미스 핀입니다 : %d";

    public InvalidMissPinsException(Pins pins) {
        super(String.format(INVALID_MISS_PINS_MESSAGE_FORMAT, pins.count()));
    }

}
