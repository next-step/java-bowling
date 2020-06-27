package bowling.domain.exceptions;

public class InvalidTryOfApplyBonusException extends RuntimeException {
    public InvalidTryOfApplyBonusException(String message) {
        super(message);
    }
}
