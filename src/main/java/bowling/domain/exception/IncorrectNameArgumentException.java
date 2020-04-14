package bowling.domain.exception;

public class IncorrectNameArgumentException extends IllegalArgumentException {
    public IncorrectNameArgumentException(String message) {
        super(message);
    }
}
