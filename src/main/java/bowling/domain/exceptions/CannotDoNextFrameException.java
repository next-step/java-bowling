package bowling.domain.exceptions;

public class CannotDoNextFrameException extends RuntimeException {
    public CannotDoNextFrameException(String message) {
        super(message);
    }
}
