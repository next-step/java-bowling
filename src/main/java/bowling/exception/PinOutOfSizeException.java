package bowling.exception;

public class PinOutOfSizeException extends RuntimeException {
    public PinOutOfSizeException(String message) {
        super(message);
    }
}
