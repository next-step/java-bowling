package bowling.domain.exceptions;

public class NotStartedPlayerException extends RuntimeException {
    public NotStartedPlayerException(String message) {
        super(message);
    }
}
