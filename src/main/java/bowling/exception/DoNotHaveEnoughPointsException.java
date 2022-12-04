package bowling.exception;

public class DoNotHaveEnoughPointsException extends RuntimeException {
    public DoNotHaveEnoughPointsException() {
        super();
    }

    public DoNotHaveEnoughPointsException(String message) {
        super(message);
    }

    public DoNotHaveEnoughPointsException(String message, Throwable cause) {
        super(message, cause);
    }

    public DoNotHaveEnoughPointsException(Throwable cause) {
        super(cause);
    }

}
