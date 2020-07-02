package bowling.domain;

import bowling.util.StringUtil;

import java.util.Objects;

public class PlayerName {

    private static final int NAME_LETTERS_SIZE = 3;

    private final String name;

    private PlayerName(String name) {
        this.name = name;
    }

    public static PlayerName of(String name) {
        checkPlayerName(name);
        return new PlayerName(name);
    }

    private static void checkPlayerName(String name) {
        if (StringUtil.length(name) != NAME_LETTERS_SIZE) {
            throw new IllegalArgumentException("이름은 3글자로 입력해주세요.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
