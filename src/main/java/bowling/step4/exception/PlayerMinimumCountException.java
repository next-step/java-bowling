package bowling.step4.exception;

public class PlayerMinimumCountException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "플레이어의 수는 1이상이여야 합니다.";

    public PlayerMinimumCountException() {
        super(ERROR_MESSAGE);
    }
}