package bowling.exception.user;

import bowling.exception.CustomException;

public class NameAlphabetPatternException extends CustomException {

    private static final String INVALID_NAME_ALPHABET_PATTERN_ERROR_MESSAGE = "이름은 영어로만 제공되어야 한다.";

    public NameAlphabetPatternException(String message) {
        super(message);
    }

    public NameAlphabetPatternException() {
        this(INVALID_NAME_ALPHABET_PATTERN_ERROR_MESSAGE);
    }

}
