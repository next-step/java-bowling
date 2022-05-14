package bowling.exception;

public class InvalidNumberOfFallenPinsException extends RuntimeException {
    private static final String MESSAGE = "ERROR] 입력 가능한 핀의 최대 갯수는 10개입니다(현재 입력: %s).";

    public InvalidNumberOfFallenPinsException(int numberOfFallenPins) {
        super(String.format(MESSAGE, numberOfFallenPins));
    }
}
