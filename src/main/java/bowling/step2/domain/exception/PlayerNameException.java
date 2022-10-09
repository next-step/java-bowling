package bowling.step2.domain.exception;

public class PlayerNameException extends IllegalArgumentException {
    public PlayerNameException(final String message) {
        super(message);
    }
}
