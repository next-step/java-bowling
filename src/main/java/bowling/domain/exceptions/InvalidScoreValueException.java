package bowling.domain.exceptions;

public class InvalidScoreValueException extends RuntimeException {
    public InvalidScoreValueException(String message) {
        super(message);
    }
}
