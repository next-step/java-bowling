package bowling.model;

import bowling.exception.UserNameException;

import java.util.Objects;

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
            throw new UserNameException();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
