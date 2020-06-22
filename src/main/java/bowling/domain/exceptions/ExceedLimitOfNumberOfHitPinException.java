package bowling.domain.exceptions;

public class ExceedLimitOfNumberOfHitPinException extends RuntimeException {
    public ExceedLimitOfNumberOfHitPinException(String message) {
        super(message);
    }
}
