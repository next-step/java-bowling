package bowling.exception;

public class IllegalPitchingException extends IllegalArgumentException {
    private static final String MESSAGE = "한 번의 투구에 대한 값은 0에서 10 사이의 숫자가 입력 되어야합니다. (입력된 값: %d)";

    public IllegalPitchingException(int value) {
        super(String.format(MESSAGE, value));
    }
}
