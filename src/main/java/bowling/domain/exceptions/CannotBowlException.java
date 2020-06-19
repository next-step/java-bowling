package bowling.domain.exceptions;

public class CannotBowlException extends RuntimeException {
    public CannotBowlException(String message) {
        super(message);
    }
}
