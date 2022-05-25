package bowling.domain;

import bowling.exception.InvalidUsernameException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Player {

    private static final String ALPHABETIC_REGEX = "^[a-zA-Z]*$";

    private static final Pattern ALPHABETIC_PATTERN = Pattern.compile(ALPHABETIC_REGEX);

    private static final int MAX_USERNAME_LENGTH = 3;

    private final String username;

    private Player(String username) {
        validateUsername(username);
        this.username = username;
    }

    public static Player create(String username) {
        return new Player(username);
    }

    private void validateUsername(String username) {
        validateUsernameAlphabetic(username);
        validateUsernameLength(username);
    }

    private void validateUsernameAlphabetic(String username) {
        Matcher matcher = ALPHABETIC_PATTERN.matcher(username);
        if (!matcher.matches()) {
            throw new InvalidUsernameException(username);
        }
    }

    private void validateUsernameLength(String username) {
        if (isBlank(username) || isInvalidUsernameLength(username)) {
            throw new InvalidUsernameException(MAX_USERNAME_LENGTH, username.length());
        }
    }

    private boolean isInvalidUsernameLength(String username) {
        return username.length() > MAX_USERNAME_LENGTH;
    }

    private boolean isBlank(String username) {
        return (username == null || username.isBlank());
    }

    @Override
    public String toString() {
        return username;
    }

}
