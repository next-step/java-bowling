package bowling.exception;

public class FrameEmptyException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "최소 1개의 프레임을 생성해야 합니다.";

    public FrameEmptyException() {
        super(DEFAULT_MESSAGE);
    }

    public FrameEmptyException(String message) {
        super(message);
    }
}
