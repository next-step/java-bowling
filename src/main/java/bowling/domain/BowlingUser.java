package bowling.domain;

import java.util.regex.Pattern;

public class BowlingUser {
    private static final int LENGTH_LIMIT = 3;
    private static final String ENG_REGEX = "^[a-zA-Z]*$";
    private static final String LENGTH_EXCEED_EXCEPTION_MESSAGE = "유저 이름의 길이는 3자를 초과할 수 없습니다.";
    private static final String INVALID_INPUT_MESSAGE = "유저 이름은 영문 이여야 합니다.";

    private String name;

    public static BowlingUser from(String name) {
        return new BowlingUser(name);
    }

    private BowlingUser(String name) {
        validateEng(name);
        validateLength(name);
        this.name = name.toUpperCase();
    }

    private void validateEng(String name) {
        if (Pattern.matches(ENG_REGEX, name)) {
            return;
        }
        throw new IllegalArgumentException(INVALID_INPUT_MESSAGE);
    }

    private void validateLength(String name) {
        if (name.length() > LENGTH_LIMIT) {
            throw new IllegalArgumentException(LENGTH_EXCEED_EXCEPTION_MESSAGE);
        }
    }

    public String getName() {
        return name;
    }
}

