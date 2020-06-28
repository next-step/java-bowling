package bowling.domain;

public class NotFinishedFrameException extends RuntimeException {
    public NotFinishedFrameException(String message) {
        super(message);
    }
}
