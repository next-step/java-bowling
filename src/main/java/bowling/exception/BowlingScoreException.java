package bowling.exception;

public class BowlingScoreException extends RuntimeException {
    public BowlingScoreException() {
        super();
    }

    public BowlingScoreException(String msg) {
        super(msg);
    }

    public BowlingScoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
