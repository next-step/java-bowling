package bowling.domain.user;

import bowling.exception.user.UsernameEnglishException;
import bowling.exception.user.UsernameLengthException;
import java.util.regex.Pattern;

public class User {

    private static final int USERNAME_MAX_LENGTH = 3;

    private static final String ENGLISH_REGEX = "^[a-zA-Z]*$";

    private final String name;

    private User(String name) {
        this.name = name;
    }

    public static User of(String name) {
        checkUsernameLength(name);
        checkUsernameEnglish(name);

        return new User(name);
    }

    private static void checkUsernameEnglish(String name) {
        if (!Pattern.matches(ENGLISH_REGEX, name)) {
            throw new UsernameEnglishException();
        }
    }

    private static void checkUsernameLength(String name) {
        if (name.length() > USERNAME_MAX_LENGTH) {
            throw new UsernameLengthException();
        }
    }

}
