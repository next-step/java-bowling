package bowling.common.exception;

public class InvalidNameInputException extends RuntimeException {
    public InvalidNameInputException() {
    }

    public InvalidNameInputException(String message) {
        super(message);
    }

    public InvalidNameInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNameInputException(Throwable cause) {
        super(cause);
    }

    public InvalidNameInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
