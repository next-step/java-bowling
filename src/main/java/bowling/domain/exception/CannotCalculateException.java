package bowling.domain.exception;

public class CannotCalculateException extends RuntimeException{
    public CannotCalculateException(String message) {
        super(message);
    }
}
