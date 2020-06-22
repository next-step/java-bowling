package bowling.domain.exceptions;

public class TooManyFrameResultsException extends RuntimeException {
    public TooManyFrameResultsException(String message) {
        super(message);
    }
}
