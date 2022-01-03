package bowling.exception;

public class FinalFrameNextException extends IllegalStateException {

    private static final String MESSAGE = "마지막 프레임 입니다.";

    public FinalFrameNextException() {
        super(MESSAGE);
    }

}
