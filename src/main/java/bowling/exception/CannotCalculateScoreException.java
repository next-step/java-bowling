package bowling.exception;

public class CannotCalculateScoreException extends RuntimeException {

    public CannotCalculateScoreException(String message) {
        super(message);
    }

}
