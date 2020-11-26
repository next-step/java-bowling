package bowling.domain.frame;

public class InvalidFrameNumberException extends RuntimeException {
    private static final String MESSAGE = "프레임 넘버는 1에서 10사이여야 합니다.";

    public InvalidFrameNumberException() {
        super(MESSAGE);
    }
}
