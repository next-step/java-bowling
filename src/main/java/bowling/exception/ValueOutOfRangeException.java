package bowling.exception;

public class ValueOutOfRangeException extends IllegalArgumentException {

    public ValueOutOfRangeException(final String message) {
        super(message);
    }
}
