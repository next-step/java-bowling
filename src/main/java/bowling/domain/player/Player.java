package bowling.domain.player;

import java.util.Objects;

public class Player {
    private final PlayerName name;
    private final int gameIndex;

    public Player(final PlayerName name, final int gameIndex) {
        this.name = name;
        this.gameIndex = gameIndex;
    }

    public boolean matchesOrder(final int gameIndex) {
        return this.gameIndex == gameIndex;
    }

    public PlayerName name() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Player player = (Player) o;
        return gameIndex == player.gameIndex && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gameIndex);
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", gameIndex=" + gameIndex + '}';
    }
}
