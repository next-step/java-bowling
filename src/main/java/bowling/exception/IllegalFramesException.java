package bowling.exception;

public class IllegalFramesException extends IllegalArgumentException {
    public IllegalFramesException(String message) {
        super(message);
    }

    public IllegalFramesException(String message, String value) {
        this(String.format(message, value));
    }
}
