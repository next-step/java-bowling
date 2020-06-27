package bowling.exception;

public class CannotCalculateException extends IllegalArgumentException {

    public CannotCalculateException(String message) {
        throw new IllegalArgumentException(message);
    }
}
