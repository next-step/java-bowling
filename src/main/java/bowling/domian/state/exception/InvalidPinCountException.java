package bowling.domian.state.exception;

public class InvalidPinCountException extends RuntimeException {
    public InvalidPinCountException() {
    }

    public InvalidPinCountException(String message) {
        super(message);
    }
}
