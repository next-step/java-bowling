package bowling.exception;

public class CannotCalculateException extends RuntimeException {

    public CannotCalculateException() {
    }

    public CannotCalculateException(String s) {
        super(s);
    }
}
