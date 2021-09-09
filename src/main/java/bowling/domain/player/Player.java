package bowling.domain.player;

import bowling.exception.PlayerNameEmptyException;
import bowling.exception.PlayerNameNotValidException;

import java.util.Objects;

public final class Player {

    public static final int NAME_LENGTH = 3;

    private final String name;

    private Player(final String name) {
        this.name = name;
    }

    public static Player valueOf(final String name) {
        validatePlayName(name);
        return new Player(name);
    }

    private static void validatePlayName(final String name) {
        if (name == null || name.isEmpty()) {
            throw new PlayerNameEmptyException();
        }

        if (name.length() != NAME_LENGTH) {
            throw new PlayerNameNotValidException();
        }
    }

    public String getName() {
        return name;
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
}
