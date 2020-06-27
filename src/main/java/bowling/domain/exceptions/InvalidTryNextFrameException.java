package bowling.domain.exceptions;

public class InvalidTryNextFrameException extends RuntimeException {
    public InvalidTryNextFrameException(String message) {
        super(message);
    }
}
