package bowling.exception;

public class InvalidPlayerCountException extends IllegalArgumentException {
    private static final String MESSAGE = "플레이어 수는 최소 1명 이상입니다. : %d";

    public InvalidPlayerCountException(int causativeValue) {
        super(String.format(MESSAGE, causativeValue));
    }
}
