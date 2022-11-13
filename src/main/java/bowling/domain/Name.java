package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {

    private final String value;

    public Name(String value) {
        validate(value);

        this.value = value;
    }

    private static void validate(String value) {
        if (value == null || value.equals("")) {
            throw new IllegalStateException("이름은 빈값일수 없습니다.");
        }

        if (value.length() > 3) {
            throw new IllegalArgumentException("이름은 3글자 이하여햐 합니다.");
        }

        if (!Pattern.matches("^[a-zA-Z]*$", value)) {
            throw new IllegalArgumentException("이름은 영어만 가능합니다.");
        }
    }

    public static Name of(String value) {
        return new Name(value);
    }

    // ====================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Name name = (Name) o;

        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Name{" +
                "value='" + value + '\'' +
                '}';
    }
}
