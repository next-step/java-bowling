package bowling.domain.player;

import java.util.Objects;

public class Name {
    private static final int NAME_MAX_LENGTH = 3;

    private final String value;

    private Name(String value) {
        this.value = value;
    }

    public static Name from(String value) {
        validateNameLength(value);
        return new Name(value);
    }

    public String value() {
        return value;
    }

    private static void validateNameLength(String value) {
        if (value.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("이름의 길이는 3이하만 허용됩니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
