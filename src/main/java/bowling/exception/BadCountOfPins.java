package bowling.exception;

public class BadCountOfPins extends RuntimeException {
    public BadCountOfPins(String message) {
        super(message);
    }
}
