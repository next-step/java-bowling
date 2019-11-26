package bowling.domain;

import java.util.Objects;

public class User {

    private final String ONLY_3WORDS_ENG_PATTERN = "^[a-zA-Z]{3,3}$";

    private String name;

    public User(String name) {
        if (!isValid(name)) {
            throw new IllegalArgumentException("플레이어 이름은 영문 3자리어야 합니다");
        }
        this.name = name;
    }

    public boolean isValid(String name) {
        return name.matches(ONLY_3WORDS_ENG_PATTERN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(ONLY_3WORDS_ENG_PATTERN, user.ONLY_3WORDS_ENG_PATTERN) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ONLY_3WORDS_ENG_PATTERN, name);
    }

    @Override
    public String toString() {
        return name;
    }
}