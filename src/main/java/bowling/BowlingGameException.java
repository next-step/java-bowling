package bowling;

public class BowlingGameException extends RuntimeException {
    public BowlingGameException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
