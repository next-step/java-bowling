package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {
    private static final int MAX_NAME_LENGTH = 3;
    private static final String ONLY_ENGLISH_PATTERN = "^[a-zA-Z]*$";

    private final String name;

    private Player(final String name) {
        this.name = name;
    }

    public static Player from(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 최대 3글자까지 가능합니다.");
        }
        if (!Pattern.matches(ONLY_ENGLISH_PATTERN, name)) {
            throw new IllegalArgumentException("이름은 영문만 입력 가능합니다.");
        }
        return new Player(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }
}
