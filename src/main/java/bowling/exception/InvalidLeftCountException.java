package bowling.exception;

public final class InvalidLeftCountException extends BowlingException {

    public static final String INVALID_LEFT_COUNT = "LeftCount는 0~2 사이의 숫자만 허용됩니다.";

    public InvalidLeftCountException() {
        super(INVALID_LEFT_COUNT);
    }
}
