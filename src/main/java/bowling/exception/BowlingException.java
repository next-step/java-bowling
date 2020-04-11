package bowling.exception;

public class BowlingException extends RuntimeException {
    private ExceptionType type;

    public BowlingException(ExceptionType type) {
        super(type.getErrorMessage());

        this.type = type;
    }
}
