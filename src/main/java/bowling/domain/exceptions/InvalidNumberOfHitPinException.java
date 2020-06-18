package bowling.domain.exceptions;

public class InvalidNumberOfHitPinException extends RuntimeException {
    public InvalidNumberOfHitPinException(String message) {
        super(message);
    }
}
