package bowling.bowlingexception;

public class InvalidPinStatusException extends IllegalArgumentException {

    public InvalidPinStatusException() {
        super("허용되지 않는 핀의 상태입니다.");
    }
}
