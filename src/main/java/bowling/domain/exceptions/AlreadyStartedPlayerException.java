package bowling.domain.exceptions;

public class AlreadyStartedPlayerException extends RuntimeException {
    public AlreadyStartedPlayerException(String message) {
        super(message);
    }
}
