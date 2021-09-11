package bowling.exception;

public class BowlingFrameException extends RuntimeException {
    public BowlingFrameException() {
        super();
    }

    public BowlingFrameException(String msg) {
        super(msg);
    }

    public BowlingFrameException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
