package bowling.ui.exceptions;

public class InvalidPlayerNameException extends RuntimeException {
    public InvalidPlayerNameException(String message) {
        super(message);
    }
}
