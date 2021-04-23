package bowling.exception;

public class PinsOutOfSizeException extends IllegalArgumentException {
    public PinsOutOfSizeException() {
    }

    public PinsOutOfSizeException(String message) {
        super(message);
    }
}
