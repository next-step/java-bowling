package bowling.domain;

import java.util.Objects;

public class User {
    private static final String NAME_FORMAT = "|  %s |";
    private static final int NAME_LENGTH = 3;

    private final String name;

    public User(String name) {
        validateName(name);
        this.name = name;
    }

    private void validateName(String name) {
        if (name == null || name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 사용자의 이름은 " + NAME_LENGTH + "글자여야 합니다.");
        }
    }

    public String mark() {
        return String.format(NAME_FORMAT, name);
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
