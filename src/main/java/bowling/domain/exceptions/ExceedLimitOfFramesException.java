package bowling.domain.exceptions;

public class ExceedLimitOfFramesException extends RuntimeException {
    public ExceedLimitOfFramesException(String message) {
        super(message);
    }
}
