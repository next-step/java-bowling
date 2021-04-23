package bowling.exception;

public class PinOutOfSizeException extends RuntimeException {
    public PinOutOfSizeException() {
    }

    public PinOutOfSizeException(String message) {
        super(message);
    }
}
