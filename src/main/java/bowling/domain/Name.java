package bowling.domain;

import bowling.exception.StringNullPointerException;

import java.util.Objects;

public final class Name {

    private final String name;

    public static Name valueOf(final String name) {
        return new Name(name);
    }

    private Name(final String name) {
        validateNull(name);
        this.name = name;
    }

    private final void validateNull(final String name) {
        if (Objects.isNull(name)) {
            throw new StringNullPointerException();
        }
    }


}
