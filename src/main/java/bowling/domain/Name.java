package bowling.domain;

import java.util.Objects;

public class Name {
    private static final int NAME_LENGTH = 3;
    private static final String INVALID_NAME = "이름은 3자여야합니다.";
    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name of(String name) {
        validName(name);
        return new Name(name);
    }

    private static void validName(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name player = (Name) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "" + name + "";
    }
}
