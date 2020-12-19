package bowling.bowlingexception;

public class InvalidDownedPinNumberException extends IllegalArgumentException {

    public InvalidDownedPinNumberException() {
        super("입력 범위를 벗어난 입력입니다.");
    }
}
