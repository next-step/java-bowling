package bowling.domain;

import bowling.exception.PlayerNameLengthException;

import java.util.Objects;

public final class PlayerName {

    private final String name;

    public PlayerName(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        final int length = name.length();
        if (length < 1 || length > 3) {
            throw new PlayerNameLengthException(name);
        }
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
        return Objects.hash(name);
    }
}
