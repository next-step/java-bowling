package bowling.domain;

import bowling.exception.ExceptionMessage;

import java.util.Objects;

public class Name {
    private static final int NAME_MAX_LENGTH = 3;
    private String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name from(String name) {
        validate(name);

        return new Name(name);
    }

    private static void validate(String name) {
        if (name == null || name.length() == 0) {
            throw new IllegalArgumentException(ExceptionMessage.NAME_NOT_NULL);
        }

        if (name.length() != NAME_MAX_LENGTH) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_NAME_LENGTH);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name that = (Name) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
