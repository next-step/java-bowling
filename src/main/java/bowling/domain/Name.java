package bowling.domain;

import bowling.exception.BusinessException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Name {
    private static final int NAME_LENGTH = 3;
    public static final String CREATE_NAME_ERROR = "플레이어 이름은 " + NAME_LENGTH + "글자의 영문자여야 합니다.";
    private static final Pattern ENGLISH = Pattern.compile("^[a-zA-Z]*$");

    private final String name;

    public Name(final String name) {
        if (name == null || name.isEmpty() || name.length() != NAME_LENGTH || !isAlphabet(name)) {
            throw new BusinessException(CREATE_NAME_ERROR);
        }
        this.name = name;
    }

    private boolean isAlphabet(final String name) {
        return ENGLISH.matcher(name).matches() && name.length() == NAME_LENGTH;
    }


    public String value() {
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
