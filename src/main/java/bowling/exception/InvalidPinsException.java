package bowling.exception;

public class InvalidPinsException extends IllegalArgumentException {
    private static final String MESSAGE = "핀은 0 부터 10 사이의 값 이어야 합니다.";

    public InvalidPinsException() {
        super(MESSAGE);
    }
}