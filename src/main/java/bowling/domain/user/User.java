package bowling.domain.user;

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
            throw new RuntimeException("유저의 이름은 영어만 들어와야 합니다.");
        }
    }

}
