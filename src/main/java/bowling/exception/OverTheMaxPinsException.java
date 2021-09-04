package bowling.exception;

public class OverTheMaxPinsException extends IllegalArgumentException {
    private static final String MESSAGE = "두 핀의 합이 10이 넘습니다.";

    public OverTheMaxPinsException() {
        super(MESSAGE);
    }
}
