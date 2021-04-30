package bowling.exception;

public final class FramePinCountException extends BowlingException {

    public static final String FRAME_PIN_COUNT_EXCEEDED = "프레임이 허용가능한 Pin의 개수를 벗어났습니다.";

    public FramePinCountException() {
        super(FRAME_PIN_COUNT_EXCEEDED);
    }
}
