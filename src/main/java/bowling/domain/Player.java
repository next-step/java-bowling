package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    private static final String NAME_LENGTH_ERROR_MESSAGE = "error : 이름의 길이는 %d 입니다.";
    private static final String NAME_LANGUAGE_ERROR_MESSAGE = "error : 이름은 영문만 사용 가능합니다.";
    private static final String NULL_ERROR_MESSAGE = "error : Null 값 은 사용할수 없습니다.";
    private static final String EMPTY_ERROR_MESSAGE = "error : 공백 값 은 사용할수 없습니다.";
    private static final String ENGLISH_PATTERN = "^[a-zA-Z]*$";
    private static final int MAX_LENGTH = 3;

    private final String name;

    public Player(String name) {
        checkNull(name);
        checkEmpty(name);
        validLength(name);
        validLanguage(name);
        this.name = name;
    }

    private void checkNull(String name) {
        if (Objects.isNull(name)) {
            throw new IllegalArgumentException(NULL_ERROR_MESSAGE);
        }
    }

    private void checkEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private void validLength(String name) {
        if (name.length() != MAX_LENGTH) {
            throw new IllegalArgumentException(String.format(NAME_LENGTH_ERROR_MESSAGE, MAX_LENGTH));
        }
    }

    private void validLanguage(String name) {
        if (!Pattern.matches(ENGLISH_PATTERN, name)) {
            throw new IllegalArgumentException(NAME_LANGUAGE_ERROR_MESSAGE);
        }
    }

    public String value() {
        return name;
    }

}
