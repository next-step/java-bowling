package bowling.bowlingexception;

public class InvalidProgressException extends IllegalArgumentException {
    public InvalidProgressException() {
        super("허용되지 않은 프레임 진행상태 입니다.");
    }
}
