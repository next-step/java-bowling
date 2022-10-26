package bowling.domain.player;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final Pattern PATTERN = Pattern.compile("a-zA-Z0-9");
    private static final int MAX_SIZE = 3;

    private final String value;

    public Name(String value) {
        validateValue(value);

        value = value.strip();
        validate(value);

        this.value = value;
    }

    private static void validateValue(String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("이름이 null 이거나 빈 문자열입니다.");
        }
    }

    private void validate(String value) {
        if (value.length() > MAX_SIZE) {
            throw new IllegalArgumentException("이름은 " + MAX_SIZE + "글자 이하이어야 합니다.");
        }

        if (!isAlpha(value)) {
            throw new IllegalArgumentException("한글은 포함될 수 없습니다.");
        }
    }

    private boolean isAlpha(String name) {
        return PATTERN.matcher(name).matches();
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Name name = (Name) o;
        return Objects.equals(value, name.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
