package bowling.exception;

public class BadSizeOfPlayersException extends RuntimeException {
    public BadSizeOfPlayersException(String message) {
        super(message);
    }
}
