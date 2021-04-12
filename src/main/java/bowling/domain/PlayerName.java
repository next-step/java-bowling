package bowling.domain;

import bowling.util.StringUtils;

import java.util.Objects;

public class PlayerName {

    private static final int MAX_LENGTH = 3;

    private final String name;

    public PlayerName(String name) {
        if (!StringUtils.isAllAlphabet(name)) {
            throw new IllegalArgumentException("영어가 아닌 문자열이 포함되어 있습니다.");
        }
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("길이가 너무 깁니다.");
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

        PlayerName that = (PlayerName) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
