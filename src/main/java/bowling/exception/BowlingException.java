package bowling.exception;

public class BowlingException extends RuntimeException {

    public BowlingException(String message) {
        super(message);
    }

    public BowlingException(BowlingExceptionCode code) {
        super(code.getMessage());
    }

    public BowlingException(BowlingExceptionCode exceptionCode, String value) {
        super(String.format("%s value - %s", exceptionCode.getMessage(), value));
    }

    public BowlingException(BowlingExceptionCode exceptionCode, int value) {
        super(String.format("%s value - %d", exceptionCode.getMessage(), value));
    }
}
