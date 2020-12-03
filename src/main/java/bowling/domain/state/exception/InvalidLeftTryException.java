package bowling.domain.state.exception;

public class InvalidLeftTryException extends RuntimeException {
    private static final String MESSAGE = "더이상 시도할 수 없습니다.";

    public InvalidLeftTryException() {
        super(MESSAGE);
    }
}
