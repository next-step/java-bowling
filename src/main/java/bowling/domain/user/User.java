package bowling.domain.user;

import java.util.Objects;

public class User {

    private static final int MAX_SIZE = 3;
    private final String name;

    private User(String name) {
        this.name = name;
    }

    public static User of(String name) {
        validateName(name);
        return new User(name);
    }

    private static void validateName(String name) {
        if (name == null || name.length() != MAX_SIZE) {
            throw new IllegalArgumentException("사용자 이름 길이는 1~3글자여야 합니다.");
        }
    }

    public String getName() {
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
