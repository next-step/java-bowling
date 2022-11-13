package bowling.domain;

import java.util.regex.Pattern;

public class User {
    private static final int MAX_NAME_SIZE = 3;
    
    private final String name;

    public User(final String name) {
        if (!isValid(name)) {
            throw new IllegalArgumentException("User name must be no more than 3 characters in English.");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    private static boolean isValid(String name) {
        if (name == null) {
            return false;
        }
        return Pattern.matches("^[a-zA-Z]{1,"+MAX_NAME_SIZE+"}$", name);
    }
}
