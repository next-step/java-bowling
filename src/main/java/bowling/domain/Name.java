package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Created By mand2 on 2020-12-18.
 */
public class Name {

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]{3}$");
    public static final String MESSAGE_NAME_PATTERN_LENGTH = "이름은 영문 대소문자 3자리로만 입력해야 합니다.";

    private final String name;

    private Name(String name) {
        this.name = name;
    }

    public static Name from(String name) {
        validate(name);

        return new Name(name);
    }

    private static void validate(String name) {
        if (!NAME_PATTERN.matcher(name).find()) {
            throw new IllegalArgumentException(MESSAGE_NAME_PATTERN_LENGTH);
        }
    }

    public String getName() {
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
