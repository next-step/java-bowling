package bowling.domain.player;

import bowling.exception.InvalidPlayerCountException;

import java.util.Objects;

public class PlayerCount {
    private static final int MIN_PLAYER_COUNT = 1;

    private final int playerCount;

    private PlayerCount(final int playerCount) {
        validPlayerCount(playerCount);
        this.playerCount = playerCount;
    }

    public static PlayerCount valueOf(final String playerCount) {
        return new PlayerCount(Integer.valueOf(playerCount));

    }

    private void validPlayerCount(final int playerCount) {
        if (playerCount < MIN_PLAYER_COUNT) {
            throw new InvalidPlayerCountException(playerCount);
        }
    }

    public int getPlayerCount() {
        return playerCount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PlayerCount that = (PlayerCount) o;
        return playerCount == that.playerCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerCount);
    }
}
