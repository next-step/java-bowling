package bowling.domain.player;

import java.util.Objects;

public class Player {
    private final PlayerName name;
    private final int gameOrder;

    public Player(final PlayerName name, final int gameOrder) {
        this.name = name;
        this.gameOrder = gameOrder;
    }

    public boolean matchesOrder(final int order) {
        return gameOrder == order;
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
        return gameOrder == player.gameOrder && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gameOrder);
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", gameOrder=" + gameOrder + '}';
    }
}
