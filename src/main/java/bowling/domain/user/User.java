package bowling.domain.user;

import bowling.exception.user.UsernameEnglishException;
import java.util.regex.Pattern;

public class User {

    private static final String ENGLISH_REGEX = "^[a-zA-Z]*$";

    private final String name;

    private User(String name) {
        this.name = name;
    }

    public static User of(String name) {
        checkUsernameEnglish(name);
        return new User(name);
    }

    private static void checkUsernameEnglish(String name) {
        if (!Pattern.matches(ENGLISH_REGEX, name)) {
            throw new UsernameEnglishException();
        }
    }

}
