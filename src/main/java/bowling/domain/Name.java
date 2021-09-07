package bowling.domain;

import bowling.exception.InputException;

import java.util.Arrays;
import java.util.Objects;

public class Name {
    private static final int NAME_LENGTH = 3;
    private static final String CREATE_NAME_ERROR = "플레이어 이름은 " + NAME_LENGTH + "글자의 영문자여야 합니다.";

    private final String name;

    public Name(final String name) {
        if (name == null || name.isEmpty() || name.length() != NAME_LENGTH || !isAlphabet(name)) {
            throw new InputException(CREATE_NAME_ERROR);
        }
        this.name = name;
    }

    private boolean isAlphabet(final String name) {
        return Arrays.stream(name.toLowerCase().split(""))
                .map(string -> string.charAt(0))
                .filter(character -> character >= 'a' && character <= 'z')
                .count() == NAME_LENGTH;
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

    public String getName() {
        return name;
    }
}
