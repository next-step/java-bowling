package bowling.exception;

public final class IllegalNormalFrameException extends BowlingException {

    public static final String ILLEGAL_NORMAL_FRAME_ROUND = "Normal Frame은 10라운드가 될 수 없습니다.";

    public IllegalNormalFrameException() {
        super(ILLEGAL_NORMAL_FRAME_ROUND);
    }
}
