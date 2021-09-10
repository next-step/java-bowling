package bowling.exception;

public class InvalidFallenPinsException extends RuntimeException {

    private static final String MESSAGE = "쓰러뜨릴 수 있는 핀의 수가 유효하지 않습니다. (입력값: %d)";

    public InvalidFallenPinsException(int fallenPins) {
        super(String.format(MESSAGE, fallenPins));
    }
}
