package bowling.domain;

import bowling.domain.exception.NameLengthExceededException;

import java.util.Objects;

public class Name {

    private String name;

    public Name(final String name) {
        checkValidNameLength(name);
        this.name = name;
    }

    private void checkValidNameLength(String name) {
        if (name.length() > 3) {
            throw new NameLengthExceededException();
        }
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
