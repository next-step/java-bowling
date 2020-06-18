package bowling.domain.exceptions;

public class TooManyThrowResultsException extends RuntimeException {
    public TooManyThrowResultsException(String message) {
        super(message);
    }
}
