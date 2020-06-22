package bowling.domain.exceptions;

public class InvalidTryBowlException extends  RuntimeException {
    public InvalidTryBowlException(String message) {
        super(message);
    }
}
