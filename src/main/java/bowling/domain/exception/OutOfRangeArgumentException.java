package bowling.domain.exception;

public class OutOfRangeArgumentException extends IllegalArgumentException {
    public OutOfRangeArgumentException(String message) {
        super(message);
    }
}
