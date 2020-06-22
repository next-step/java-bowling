package bowling.domain.exceptions;

public class NoFrameStatusException extends RuntimeException {
    public NoFrameStatusException(String message) {
        super(message);
    }
}
