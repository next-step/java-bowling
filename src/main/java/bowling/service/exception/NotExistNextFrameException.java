package bowling.service.exception;

public class NotExistNextFrameException extends RuntimeException {
    public NotExistNextFrameException(String message) {
        super(message);
    }
}
