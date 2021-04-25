package bowling.exception;

public final class TooManyPinCountException extends BowlingException {

    public static final String TOO_MANY_PIN_COUNT = "핀의 개수가 허용된 값보다 더 많습니다.";

    public TooManyPinCountException() {
        super(TOO_MANY_PIN_COUNT);
    }
}
