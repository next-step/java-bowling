package bowling.exception;

public class InvalidPinsException extends RuntimeException {

    private static final String INVALID_PINS_MESSAGE_FORMAT = "올바르지 않은 핀 입력 값입니다.:%d";

    public InvalidPinsException(int number) {
        super(String.format(INVALID_PINS_MESSAGE_FORMAT, number));
    }

}
