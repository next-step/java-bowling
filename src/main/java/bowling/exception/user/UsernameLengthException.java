package bowling.exception.user;

import bowling.exception.CustomException;

public class UsernameLengthException extends CustomException {

    private static final String USERNAME_LENGTH_ERROR_MESSAGE = "유저의 이름은 3글자 이하로 들어와야 합니다.";

    public UsernameLengthException(String message) {
        super(message);
    }

    public UsernameLengthException() {
        this(USERNAME_LENGTH_ERROR_MESSAGE);
    }

}
