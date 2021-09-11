package bowling.domain.user;

import java.util.regex.Pattern;
import org.apache.logging.log4j.util.Strings;

public class Name {

    private static final String INVALID_NAME_NULL_AND_BLANK_ERROR_MESSAGE = "이름은 반드시 제공되어야 한다.";
    private static final String INVALID_NAME_LENGTH_ERROR_MESSAGE = "이름은 3글자 이하로 입력되어야 한다.";
    private static final String INVALID_NAME_ALPHABET_PATTERN_ERROR_MESSAGE = "이름은 영어로만 제공되어야 한다.";

    private static final String ALPHABET_REGEX = "^[a-zA-Z]*$";

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        checkNameNullAndBlankException(name);
        checkNameLengthException(name);
        checkNameAlphabetException(name);

        return new Name(name);
    }

    private static void checkNameLengthException(String name) {
        if (name.length() > 3) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH_ERROR_MESSAGE);
        }
    }

    private static void checkNameNullAndBlankException(String name) {
        if (Strings.isBlank(name)) {
            throw new IllegalArgumentException(INVALID_NAME_NULL_AND_BLANK_ERROR_MESSAGE);
        }
    }

    private static void checkNameAlphabetException(String name) {
        if (!Pattern.matches(ALPHABET_REGEX, name)){
            throw new IllegalArgumentException(INVALID_NAME_ALPHABET_PATTERN_ERROR_MESSAGE);
        }
    }

}
