package bowling.util;

import bowling.exception.NotSupportInstanceException;

import java.util.regex.Pattern;

public class LanguageUtil {

    private static final String PATTERN_ENGLISH = "^[a-zA-Z]*$";

    private LanguageUtil() {
        throw new NotSupportInstanceException();
    }

    public static boolean isEnglish(String value) {
        return Pattern.matches(PATTERN_ENGLISH, value);
    }
}
