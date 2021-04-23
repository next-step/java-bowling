package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final int NAME_LENGTH_STANDARD = 3;
    private static final String ALPHABET_PATTERN = "^[a-zA-Z]*$";
    private static final String CHECK_NULL_OR_EMPTY = "null 또는 공백인지 확인해주세요.";
    private static final String CHECK_ALPHABET = "영문 이름인지 확인해주세요.";
    private static final String CHECK_NAME_LENGTH = "이름이 3자리인지 확인해주세요.";

    private final String name;

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    private void validate(String name) {
        checkNullOrEmpty(name);
        checkAlphabet(name);
        checkLength(name);
    }

    private void checkNullOrEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(CHECK_NULL_OR_EMPTY);
        }
    }

    private void checkAlphabet(String name) {
        if (!Pattern.matches(ALPHABET_PATTERN, name)) {
            throw new IllegalArgumentException(CHECK_ALPHABET);
        }
    }

    private void checkLength(String name) {
        if (name.length() != NAME_LENGTH_STANDARD) {
            throw new IllegalArgumentException(CHECK_NAME_LENGTH);
        }
    }

    public String name() {
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
