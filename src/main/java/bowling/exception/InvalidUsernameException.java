package bowling.exception;

public class InvalidUsernameException extends RuntimeException {

    private static final String INVALID_USERNAME_LENGTH_FORMAT = "사용자 이름은 빈칸 또는 %d 글자는 불가능 합니다. (length : %d)";

    private static final String INVALID_USERNAME_ALPHABETIC_FORMAT = "사용자의 이름은 영문만 가능합니다. (username : %s)";

    public InvalidUsernameException(int maxUsernameLength, int usernameLength) {
        super(String.format(INVALID_USERNAME_LENGTH_FORMAT, maxUsernameLength, usernameLength));
    }

    public InvalidUsernameException(String username) {
        super(String.format(INVALID_USERNAME_ALPHABETIC_FORMAT, username));
    }

}
