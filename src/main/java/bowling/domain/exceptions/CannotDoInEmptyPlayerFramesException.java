package bowling.domain.exceptions;

public class CannotDoInEmptyPlayerFramesException extends RuntimeException {
    public CannotDoInEmptyPlayerFramesException(String message) {
        super(message);
    }
}
