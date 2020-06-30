package bowling.domain.exception;

public class FramesMaximumException extends RuntimeException {
    private static final String FINAL_FRAME_LIMIT = "플레이할 수 있는 프레임은 10 프레임이 마지막입니다.";

    public FramesMaximumException() {
        super(FINAL_FRAME_LIMIT);
    }
}
