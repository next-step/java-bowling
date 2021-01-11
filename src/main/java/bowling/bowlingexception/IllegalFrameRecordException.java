package bowling.bowlingexception;

public class IllegalFrameRecordException extends RuntimeException {
    public IllegalFrameRecordException() {
        super("해당 프레임이 종료된 이후에도 추가적인 기록이 요청되었습니다.");
    }
}
