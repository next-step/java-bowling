package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;

import java.util.regex.Pattern;

public class Player {

    private static final int VALID_LENGTH = 3;

    private static final String VALID_NAME_REGEX = "[A-Za-z]+";

    private String name;

    public Player(String name) {
        if (!valid(name)) {
            throw new CustomException(ErrorCode.INVALID_NAME);
        }
        this.name = name;
    }

    private boolean valid(String name) {
        return Pattern.matches(VALID_NAME_REGEX, name) && name.length() == VALID_LENGTH;
    }

    public String name() {
        return name;
    }
}
