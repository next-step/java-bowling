package bowling.bowlingexception;

public class IllegalPinRangeException extends IllegalArgumentException {

    public IllegalPinRangeException() {
        super("범위를 초과한 입력입니다. 넘어진 핀 갯수는 0 ~ 10 사이 입니다.");
    }
}
