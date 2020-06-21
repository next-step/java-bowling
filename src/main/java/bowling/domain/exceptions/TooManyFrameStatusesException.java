package bowling.domain.exceptions;

public class TooManyFrameStatusesException extends  RuntimeException {
    public TooManyFrameStatusesException(String message) {
        super(message);
    }
}
