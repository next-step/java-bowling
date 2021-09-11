package step2.exceptions;

public class UserNameLengthError extends IllegalArgumentException {
    private static final String PLAYER_NAME_LENGTH_ERROR_MESSAGE = "플레이어이의 이름은 3글자로 이루어져야 합니다.";
    public UserNameLengthError() {
        super(PLAYER_NAME_LENGTH_ERROR_MESSAGE);
    }
}
