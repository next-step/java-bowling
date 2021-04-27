package bowling.domain.exception;

public class NoRemainingFrameException extends RuntimeException {
    private static final String MESSAGE = "다음 프레임이 존재하지 않습니다.";
    public NoRemainingFrameException() {
        super(MESSAGE);
    }
}
