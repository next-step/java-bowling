package bowling.exception;

public class InvalidFrameNumberException extends RuntimeException {

    private static final String MESSAGE = "유효하지 않은 프레임 번호입니다. (입력값: %d)";

    public InvalidFrameNumberException(int frameNumber) {
        super(String.format(MESSAGE, frameNumber));
    }
}
