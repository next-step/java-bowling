package bowling.exception;

public class BowlingPlayerException extends RuntimeException {
    public BowlingPlayerException() {
        super();
    }

    public BowlingPlayerException(String msg) {
        super(msg);
    }

    public BowlingPlayerException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
