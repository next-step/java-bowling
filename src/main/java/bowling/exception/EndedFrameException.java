package bowling.exception;

public class EndedFrameException extends RuntimeException {

    private static final String MESSAGE = "이미 종료된 프레임입니다.";

    public EndedFrameException() {
        super(MESSAGE);
    }
}
