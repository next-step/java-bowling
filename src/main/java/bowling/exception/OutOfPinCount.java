package bowling.exception;

public class OutOfPinCount extends IllegalArgumentException {
    private final static String value = "쓰러트린 볼링 핀의 개수는 0과 10사이의 숫자여야 합니다.";

    public OutOfPinCount() {
        super(value);
    }
}
