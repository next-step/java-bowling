package bowling.exception;

public class CannotCalculateException extends Exception {
    private static final long serialVersionUID = 1L;

    public CannotCalculateException(String message) {
        super(message);
    }
}
