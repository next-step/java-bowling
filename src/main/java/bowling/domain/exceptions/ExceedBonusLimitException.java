package bowling.domain.exceptions;

public class ExceedBonusLimitException extends RuntimeException {
    public ExceedBonusLimitException(String message) {
        super(message);
    }
}
