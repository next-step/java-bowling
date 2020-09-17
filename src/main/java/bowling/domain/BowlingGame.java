package bowling.domain;

import java.util.Objects;

public class BowlingGame {
    private final Player player;

    public BowlingGame(String playerName) {
        this.player = new Player(playerName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BowlingGame that = (BowlingGame) o;
        return Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player);
    }
}
