package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

import java.util.regex.Pattern;

public class Name {

    private static final int VALID_LENGTH = 3;

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
        return Pattern.matches("[A-Za-z]+", name) && name.length() == VALID_LENGTH;
    }

    public String name() {
        return this.name;
    }
}
