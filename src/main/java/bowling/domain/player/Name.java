package bowling.domain.player;

import bowling.utils.StringUtil;

import java.util.Objects;

public class Name {

    private static final int LENGTH = 3;
    private static final String REG_EXP = "^[a-zA-Z]*$";

    private final String value;

    public Name(String value) {
        validateBlank(value);
        validateLength(value);
        validateLetters(value);
        this.value = value.trim();
    }

    private void validateBlank(String value) {
        if (StringUtil.isBlank(value)) {
            throw new NameLengthException();
        }
    }

    private void validateLength(String value) {
        if (value.trim().length() != LENGTH) {
            throw new NameLengthException(value.trim().length());
        }
    }

    private void validateLetters(String value) {
        if (!value.matches(REG_EXP)) {
            throw new NameLettersException();
        }
    }

    public String value() {
        return value;
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
