package bowling.domain.player;

import bowling.exception.BowlingException;

import java.util.Objects;

public class Player {

    private static final String PLAYER_NAME_LENGTH_ERR_MESSAGE = "이름은 3글자만 가능 합니다.";

    private final String name;

    public Player(final String name) {
        validateNameLength(name);
        this.name = name;
    }

    private void validateNameLength(final String name) {
        if (name.length() != 3) {
            throw new BowlingException(PLAYER_NAME_LENGTH_ERR_MESSAGE);
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
