package bowling.player.exception;

public class NotMatchingPlayerNameException extends IllegalArgumentException {

    public NotMatchingPlayerNameException(String message) {
        super(message);
    }

}
