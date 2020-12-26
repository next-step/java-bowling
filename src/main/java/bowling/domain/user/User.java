package bowling.domain.user;

import bowling.bowlingexception.InvalidNameFormatException;
import bowling.dto.UserDTO;

import java.util.regex.Pattern;

public class User {
    private static final String allowedPattern = "^[a-zA-Z]{3}$";

    private final String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || !Pattern.matches(allowedPattern, name)) {
            throw new InvalidNameFormatException();
        }
    }

    public UserDTO exportData() {
        return new UserDTO(name);
    }
}
