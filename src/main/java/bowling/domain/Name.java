package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

import java.util.regex.Pattern;

public class Name {

    private static final int VALID_LENGTH = 3;

    private static final String VALID_NAME_REGEX = "[A-Za-z]+";

    private final String name;

    public Name(String name) {
        this.name = verifiedName(name);
    }

    private String verifiedName(String name) {
        name = name.trim();
        if (!valid(name)) {
            throw new CustomException(ErrorCode.INVALID_NAME);
        }
        return name;
    }

    private boolean valid(String name) {
        return Pattern.matches(VALID_NAME_REGEX, name) && name.length() == VALID_LENGTH;
    }

    public String name() {
        return this.name;
    }
}
