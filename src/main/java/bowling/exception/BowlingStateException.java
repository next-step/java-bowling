package bowling.exception;

public class BowlingStateException extends RuntimeException {
    public BowlingStateException() {
        super();
    }

    public BowlingStateException(String msg) {
        super(msg);
    }

    public BowlingStateException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
