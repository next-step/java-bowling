package bowling.domain;

import java.util.Objects;

public class Name {
    public static final String ERR_MSG_NAME_LENGTH = "이름은 3글자여야 합니다.";
    private final String name;

    public Name(String name) {
        if (name.length() != 3) {
            throw new IllegalArgumentException(ERR_MSG_NAME_LENGTH);
        }
        this.name = name;
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
