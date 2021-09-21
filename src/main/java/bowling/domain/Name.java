package bowling.domain;

import bowling.domain.exception.NameLengthExceededException;
import bowling.domain.exception.NotEnglishNameException;

import java.util.Objects;

public class Name {

    private static final int MAX_NAME_LENGTH = 3;
    private static final String ALL_ENGLISH_REGEX = "[a-zA-Z]+";

    private String name;

    private Name(final String name) {
        checkValidNameLength(name);
        checkValidEnglishName(name);
        this.name = name;
    }

    public static Name of(final Name name) {
        return new Name(name.name);
    }

    public static Name of(final String name) {
        return new Name(name);
    }

    private void checkValidNameLength(String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new NameLengthExceededException();
        }
    }

    private void checkValidEnglishName(final String name) {
        if (!name.matches(ALL_ENGLISH_REGEX)) {
            throw new NotEnglishNameException();
        }
    }

    public String value() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
