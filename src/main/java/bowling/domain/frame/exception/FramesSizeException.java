package bowling.domain.frame.exception;

public class FramesSizeException extends RuntimeException {

    private static final String MESSAGE = "유효하지 않은 길이의 프레임";

    public FramesSizeException() {
        super(MESSAGE);
    }
}
