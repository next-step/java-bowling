package bowling.exception;

import static bowling.exception.ExceptionConstants.INVALID_USERNAME_ALPHABETIC_FORMAT;
import static bowling.exception.ExceptionConstants.INVALID_USERNAME_LENGTH_FORMAT;

public class InvalidUsernameException extends RuntimeException {

    public InvalidUsernameException(int maxUsernameLength, int usernameLength) {
        super(String.format(INVALID_USERNAME_LENGTH_FORMAT, maxUsernameLength, usernameLength));
    }

    public InvalidUsernameException(String username) {
        super(String.format(INVALID_USERNAME_ALPHABETIC_FORMAT, username));
    }

}
