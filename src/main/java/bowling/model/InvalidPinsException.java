package bowling.model;

public class InvalidPinsException extends IllegalArgumentException {

    private static final String ERROR_MESSAGE = "핀은 %d ~ %d 사이 값이어야 합니다 (입력값: %s)";

    public InvalidPinsException(int min, int max, Pins inputNumber) {
        super(String.format(ERROR_MESSAGE, min, max, inputNumber));
    }

    InvalidPinsException(int min, int max, int inputNumber) {
        super(String.format(ERROR_MESSAGE, min, max, inputNumber));
    }
}
