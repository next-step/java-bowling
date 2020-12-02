package bowling.exception;

public class BadCountOfPinsException extends RuntimeException {
    public BadCountOfPinsException(String message) {
        super(message);
    }
}
