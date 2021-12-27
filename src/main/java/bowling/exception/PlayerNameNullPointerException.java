package bowling.exception;

public class PlayerNameNullPointerException extends NullPointerException {

    private static final String MESSAGE = "플레이어명은 필수 입니다.";

    public PlayerNameNullPointerException() {
        super(MESSAGE);
    }

}
