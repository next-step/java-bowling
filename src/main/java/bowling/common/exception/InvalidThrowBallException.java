package bowling.common.exception;

public class InvalidThrowBallException extends RuntimeException {
    public InvalidThrowBallException() {
    }

    public InvalidThrowBallException(String message) {
        super(message);
    }

    public InvalidThrowBallException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidThrowBallException(Throwable cause) {
        super(cause);
    }

    public InvalidThrowBallException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
