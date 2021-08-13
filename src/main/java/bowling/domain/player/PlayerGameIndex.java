package bowling.domain.player;

import bowling.exception.InvalidPlayerIndexException;

import java.util.Objects;

public class PlayerGameIndex {
    private final int gameIndex;

    public PlayerGameIndex(final int gameIndex) {
        validateNegative(gameIndex);
        this.gameIndex = gameIndex;
    }

    private void validateNegative(int gameIndex) {
        if (gameIndex < 0) {
            throw new InvalidPlayerIndexException();
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PlayerGameIndex that = (PlayerGameIndex) o;
        return gameIndex == that.gameIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameIndex);
    }

    @Override
    public String toString() {
        return "PlayerGameOrder{" + "gameOrder=" + gameIndex + '}';
    }
}
