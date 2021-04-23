package bowling.exception;

public class BowlingException extends RuntimeException {

    public BowlingException(String message) {
        super(message);
    }

    public BowlingException(String message, Throwable cause) {
        super(message, cause);
    }

    public BowlingException(Throwable cause) {
        super(cause);
    }
}
