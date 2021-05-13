package bowling.exception;

public final class InvalidPinsSizeException extends RuntimeException {

    private final String MESSAGE = "Pins 의 범위를 벗어난 값이 입력되었습니다.";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}