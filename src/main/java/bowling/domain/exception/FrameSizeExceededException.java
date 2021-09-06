package bowling.domain.exception;

public class FrameSizeExceededException extends RuntimeException {

    public FrameSizeExceededException() {
        super("프레임은 10개를 넘을 수 없습니다.");
    }
}
