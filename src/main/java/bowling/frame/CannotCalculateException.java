package bowling.frame;

public class CannotCalculateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CannotCalculateException() {
        super();
    }

    public CannotCalculateException(String message) {
        super(message);
    }

}
