package bowling.exception.user;

import bowling.exception.CustomException;

public class NameBlankException extends CustomException {

    private static final String INVALID_NAME_NULL_AND_BLANK_ERROR_MESSAGE = "이름은 반드시 제공되어야 한다.";

    public NameBlankException(String message) {
        super(message);
    }

    public NameBlankException() {
        this(INVALID_NAME_NULL_AND_BLANK_ERROR_MESSAGE);
    }

}
