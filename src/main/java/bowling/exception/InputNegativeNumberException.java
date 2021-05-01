package bowling.exception;

public final class InputNegativeNumberException extends RuntimeException {

    private final String MESSAGE_FORMAT = "( %s ) 는 음수 값이여서 입력 할 수 없습니다.";

    public InputNegativeNumberException(int message) {
        super(String.valueOf(message));
    }

    @Override
    public String getMessage() {
        return String.format(MESSAGE_FORMAT, super.getMessage());
    }
}
