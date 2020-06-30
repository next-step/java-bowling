package bowling.domain.exception;

public class PlayerNameInvalidException extends IllegalArgumentException {
    private static final String INVALID_PLAYER_NAME = "이름은 3글자의 문자열이어야 합니다.";

    public PlayerNameInvalidException() {
        super(INVALID_PLAYER_NAME);
    }
}
