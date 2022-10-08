package bowling.step2.domain.exception;

public class BowlException extends IllegalArgumentException {
    public BowlException(final String message) {
        super(message);
    }
}
