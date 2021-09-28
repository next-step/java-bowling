package bowling.exception.user;

import bowling.exception.CustomException;

public class UsernameEnglishException extends CustomException {

    private static final String USERNAME_ENGLISH_PATTERN_ERROR_MESSAGE = "유저의 이름은 영어만 들어와야 합니다.";

    public UsernameEnglishException(String message) {
        super(message);
    }

    public UsernameEnglishException() {
        this(USERNAME_ENGLISH_PATTERN_ERROR_MESSAGE);
    }

}
