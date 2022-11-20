package bowling.domain.exception;

public class EndedFrameException extends RuntimeException {

    public EndedFrameException() {
        super("해당 프레임은 종료되었습니다.");
    }

}
