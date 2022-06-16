package bowling.exception;

public class BowlingException extends RuntimeException{

    public BowlingException(BowlingExceptionCode exceptionCode, String value) {
        super(String.format("%s value - %s", exceptionCode.getMessage(), value));
    }
}
