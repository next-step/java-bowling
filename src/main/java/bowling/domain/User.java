package bowling.domain;

import java.util.Objects;

public class User {

    private final String name;

    public User(String name) {
        if (name.length() != 3) {
            throw new IllegalArgumentException("이름은 세글자 입니다.");
        }
        this.name = name;
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
