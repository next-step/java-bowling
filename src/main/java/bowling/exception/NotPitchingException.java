package bowling.exception;

public class NotPitchingException extends IllegalArgumentException {
    public NotPitchingException(String message) {
        super(message);
    }
}
