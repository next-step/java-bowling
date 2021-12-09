package bowling.domain.user;

import java.util.Objects;
import java.util.regex.Pattern;

public class User {

    private static final int NAME_LENGTH = 3;
    private static final String ALPHABET_REGEX = "^[a-zA-Z]*$";

    private final String name;

    private User(String name) {
        this.name = name;
    }

    public static User from(String name) {
        validLength(name);
        validAlphabet(name);

        return new User(name);
    }

    private static void validAlphabet(String name) {
        if (!Pattern.matches(ALPHABET_REGEX, name)) {
            throw new IllegalArgumentException();
        }
    }

    private static void validLength(String name) {
        if(name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
