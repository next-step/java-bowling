package bowling.model;

import bowling.ExceptionMessages;

public class User {
    private static final int MAX_NAME_LENGTH = 3;

    private final String name;

    private User(String name) {
        this.name = name;
    }

    public static User valueOf(String name) {
        validateName(name);
        return new User(name);
    }

    private static void validateName(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ExceptionMessages.USER_NAME_EXCEPTION);
        }
    }

    public String getName() {
        return name;
    }

}
