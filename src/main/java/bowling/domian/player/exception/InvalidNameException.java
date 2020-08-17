package bowling.domian.player.exception;

public class InvalidNameException extends RuntimeException {

    public InvalidNameException() {
    }

    public InvalidNameException(String message) {
        super(message);
    }
}
