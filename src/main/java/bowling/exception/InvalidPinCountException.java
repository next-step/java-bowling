package bowling.exception;

public final class InvalidPinCountException extends BowlingException {

    public static final String INVALID_PIN_COUNT = "유효하지 않은 핀의 개수입니다. 유효한 핀의 개수는 0~10입니다.";

    public InvalidPinCountException() {
        super(INVALID_PIN_COUNT);
    }
}
