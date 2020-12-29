package bowling.bowlingexception;

public class InvalidFrameStatusException extends IllegalArgumentException {
    public InvalidFrameStatusException() {
        super("허용되지 않는 상태입니다.");
    }
}
