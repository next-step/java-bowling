package bowling.domain.frame.exception;

public class InvalidFrameRecordActionException extends RuntimeException {
    private static final String MESSAGE = "해당 프레임에 더이상 점수를 기록할 수 없습니다.";

    public InvalidFrameRecordActionException() {
        super(MESSAGE);
    }
}
