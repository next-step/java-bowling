package bowling.exception;

public class BowlingException extends RuntimeException {
    public BowlingException(ExceptionType type) {
        super(type.getErrorMessage());
    }
}
