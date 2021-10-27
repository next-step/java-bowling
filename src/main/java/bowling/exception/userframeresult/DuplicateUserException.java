package bowling.exception.userframeresult;

import bowling.exception.CustomException;

public class DuplicateUserException extends CustomException {

    private static final String DUPLICATION_USER_ERROR_MESSAGE = "동일한 이름의 유저를 더할 수 없습니다.";

    public DuplicateUserException(String message) {
        super(message);
    }

    public DuplicateUserException() {
        this(DUPLICATION_USER_ERROR_MESSAGE);
    }

}
