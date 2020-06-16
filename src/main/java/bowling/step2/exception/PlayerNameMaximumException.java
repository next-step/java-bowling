package bowling.step2.exception;

public class PlayerNameMaximumException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "플레이어 이름은 최대 3글자로 입력해주세요.";
    public PlayerNameMaximumException() {
        super(ERROR_MESSAGE);
    }
}