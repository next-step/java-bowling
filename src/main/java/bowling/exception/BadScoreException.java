package bowling.exception;

public class BadScoreException extends RuntimeException {
    public BadScoreException(String message) {
        super(message);
    }
}

