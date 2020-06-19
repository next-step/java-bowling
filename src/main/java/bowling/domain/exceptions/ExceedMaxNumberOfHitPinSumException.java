package bowling.domain.exceptions;

public class ExceedMaxNumberOfHitPinSumException extends RuntimeException {
    public ExceedMaxNumberOfHitPinSumException(String message) {
        super(message);
    }
}
