package bowling.exception;

public final class BowlFailureException extends RuntimeException {
    private static final String DEFAULT_ERROR_MESSAGE = "남은 투구 기회가 없습니다.";

    public BowlFailureException() {
        super(DEFAULT_ERROR_MESSAGE);
    }

    public BowlFailureException(String message) {
        super(message);
    }
}
