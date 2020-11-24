package step2.exception;

public class OutOfRangeFrameException extends RuntimeException {
    public OutOfRangeFrameException() {
    }

    public OutOfRangeFrameException(String message) {
        super(message);
    }
}
