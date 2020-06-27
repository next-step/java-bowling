package bowling.domain.exceptions;

public class CannotCalculateFrameResultException extends RuntimeException {
    public CannotCalculateFrameResultException(String message) {
        super(message);
    }
}
