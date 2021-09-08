package bowling.domain.exception;

public class FinishFrameException extends RuntimeException {

    public FinishFrameException() {
        super("해당 프레임은 끝났습니다.");
    }
}
