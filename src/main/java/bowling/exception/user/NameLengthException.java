package bowling.exception.user;

import bowling.exception.CustomException;

public class NameLengthException extends CustomException {

    private static final String INVALID_NAME_LENGTH_ERROR_MESSAGE = "이름은 3글자 이하로 입력되어야 한다.";

    public NameLengthException(String message) {
        super(message);
    }

    public NameLengthException() {
        this(INVALID_NAME_LENGTH_ERROR_MESSAGE);
    }

}
