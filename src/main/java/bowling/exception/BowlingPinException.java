package bowling.exception;

public class BowlingPinException extends RuntimeException {
    public BowlingPinException() {
        super();
    }

    public BowlingPinException(String msg) {
        super(msg);
    }

    public BowlingPinException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
