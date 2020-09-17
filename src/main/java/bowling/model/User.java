package bowling.model;

import bowling.ExceptionMessages;

public class User {
    private static final int MAX_NAME_LENGTH = 3;

    private final String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(ExceptionMessages.USER_NAME_EXCEPTION);
        }
    }

    public String getName() {
        return name;
    }

}
