package bowling.exception;

public class FrameNotCorrectException extends IllegalArgumentException {

    private static final String MESSAGE = "프레임에 입력된 값이 올바르지 않습니다.";

    public FrameNotCorrectException() {
        super(MESSAGE);
    }
}
