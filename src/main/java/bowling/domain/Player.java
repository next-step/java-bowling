package bowling.domain;

import java.util.Objects;
import java.util.regex.Pattern;

public class Player {

    private static final String INVALID_LENTH = "플레이어 이름은 3글자만 가능합니다 -> %s";
    private static final String NOT_ENGLISH = "플레이어 이름은 영어만 가능합니다 -> %s";
    private static final int NAME_LENGTH = 3;
    private static final String ENGLISH_PATTERN = "^[a-zA-Z]*$";

    private final String name;

    public Player(final String name) {
        validateName(name);
        this.name = name;
    }

    public String name() {
        return name;
    }

    private void validateName(final String name) {
        requireValidLength(name);
        requireEnglish(name);
    }

    private void requireEnglish(final String name) {
        if (!Pattern.matches(ENGLISH_PATTERN, name)) {
            throw new IllegalArgumentException(String.format(NOT_ENGLISH, name));
        }
    }

    private void requireValidLength(final String name) {
        if (name.length() != NAME_LENGTH) {
            throw new IllegalArgumentException(String.format(INVALID_LENTH, name));
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
