package bowling.exception;

public class BadCountOfDownPinsException extends RuntimeException {
    public BadCountOfDownPinsException(String message) {
        super(message);
    }
}
