package bowling.exception;

public class RollsOutOfRangeException extends RuntimeException {
    public RollsOutOfRangeException(String message) {
        super(message);
    }
}
