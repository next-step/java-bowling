package bowling.domain;

import bowling.util.StringLengthRange;

import java.util.Objects;

public class Name {
    private static final StringLengthRange VALID_NAME_LENGTH = new StringLengthRange(1, 3);

    private final String name;

    public Name(String name) {
        VALID_NAME_LENGTH.orThrow(name, () ->
                new IllegalArgumentException("이름의 길이가 올바르지 않습니다.")
        );

        this.name = name;
    }


    public String toString() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}