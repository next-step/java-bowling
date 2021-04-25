package bowling.domain;

import bowling.exception.PlayerNameLengthException;

import java.util.Objects;

public final class PlayerName {

    private static final int MIN_LENGTH = 1;
    private static final int MAX_LENGTH = 3;

    private final String name;

    public PlayerName(String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(String name) {
        final int length = name.length();
        if (length < MIN_LENGTH || length > MAX_LENGTH) {
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
