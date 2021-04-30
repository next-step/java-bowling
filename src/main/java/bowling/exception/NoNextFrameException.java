package bowling.exception;

public final class NoNextFrameException extends BowlingException {

    public static final String FINAL_FRAME_CANNOT_CREATE_NEXT_FRAME = "마지막 프레임의 다음 프레임은 없습니다.";

    public NoNextFrameException() {
        super(FINAL_FRAME_CANNOT_CREATE_NEXT_FRAME);
    }
}
