package bowling.domain.player;

import java.util.Objects;

public class PlayerCount {
    private final int playerCount;

    private PlayerCount(int playerCount) {
        validate(playerCount);
        this.playerCount = playerCount;
    }

    public static PlayerCount of(int playerCount) {
        return new PlayerCount(playerCount);
    }

    public static PlayerCount of(String playerCount) {
        return new PlayerCount(Integer.parseInt(playerCount));
    }

    private void validate(int playerCount) {
        if (playerCount < 0) {
            throw new IllegalArgumentException("유효하지 않은 플레이어 수 입니다.");
        }
    }

    public int toInteger() {
        return this.playerCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlayerCount that = (PlayerCount)o;
        return playerCount == that.playerCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerCount);
    }
}
