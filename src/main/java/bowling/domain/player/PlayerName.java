package bowling.domain.player;

import bowling.exception.InvalidNameException;
import bowling.exception.NameLengthOverException;

import java.util.Objects;

public class PlayerName {
    private static final int NAME_MAX_LENGTH = 3;

    private final String name;

    private PlayerName(final String name) {
        this.name = name;
    }

    public static PlayerName of(final String name) {
        validName(name);
        validNameLength(name);
        return new PlayerName(name);
    }

    private static void validName(final String name) {
        if (Objects.isNull(name) || name.trim().isEmpty()) {
            throw new InvalidNameException(name);
        }
    }

    private static void validNameLength(final String name) {
        if (name.length() > NAME_MAX_LENGTH) {
            throw new NameLengthOverException(name);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PlayerName that = (PlayerName) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
