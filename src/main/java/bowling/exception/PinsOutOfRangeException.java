package bowling.exception;

public class PinsOutOfRangeException extends RuntimeException {
    public PinsOutOfRangeException(String message) {
        super(message);
    }
}
