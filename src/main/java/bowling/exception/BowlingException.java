package bowling.exception;

public class BowlingException extends RuntimeException {

    private static final String MESSAGE = "볼링 예외 : %s";

    public BowlingException(String message) {
        super(String.format(MESSAGE, message));
    }
}
