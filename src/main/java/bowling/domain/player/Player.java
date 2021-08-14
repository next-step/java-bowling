package bowling.domain.player;

import java.util.Objects;

public class Player implements Comparable<Player> {
    private final PlayerName name;
    private final PlayerGameIndex gameIndex;

    public Player(final PlayerName name, final PlayerGameIndex gameIndex) {
        this.name = name;
        this.gameIndex = gameIndex;
    }

    public boolean matchesOrder(final PlayerGameIndex gameIndex) {
        return this.gameIndex.equals(gameIndex);
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

    @Override
    public int compareTo(final Player rightPlayer) {
        return this.gameIndex.compareTo(rightPlayer.gameIndex);
    }
}
