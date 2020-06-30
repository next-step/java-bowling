package bowling.domain.player;

import bowling.exception.PlayerCountOutOfRangeException;

import java.util.Objects;

public class PlayerCount {

    private static final int MIN_PLAYER_COUNT = 1;

    private final int count;

    private PlayerCount(final int count) {
        validateCount(count);
        this.count = count;
    }

    public static PlayerCount of(final int count) {
        return new PlayerCount(count);
    }

    private void validateCount(final int playerCount) {
        if (playerCount < MIN_PLAYER_COUNT) {
            throw new PlayerCountOutOfRangeException(playerCount);
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerCount)) return false;
        PlayerCount that = (PlayerCount) o;
        return count == that.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count);
    }
}
