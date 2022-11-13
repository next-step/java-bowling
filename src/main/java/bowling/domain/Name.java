package bowling.domain;

import java.util.Objects;

public class Name {

    private static final int NAME_LENGTH = 3;
    private static final String ENGLISH_REGEX = "^[a-zA-Z]*$";
    private final String name;

    public Name(String name) {
        validateLength(name);
        validateEnglish(name);
        this.name = name;
    }

    private void validateLength(String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 " + NAME_LENGTH + "글자여야 합니다.");
        }
    }

    private void validateEnglish(String name) {
        if (!name.matches(ENGLISH_REGEX)) {
            throw new IllegalArgumentException("영어여야 합니다.");
        }
    }

    public String name() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Name name1 = (Name) o;
        return Objects.equals(this.name, name1.name);
    }
}
