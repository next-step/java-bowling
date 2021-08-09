package bowling.domain;

import java.util.Objects;

public class Player {
    private final Name name;
    private final int gameOrder;

    public Player(final Name name, final int gameOrder) {
        this.name = name;
        this.gameOrder = gameOrder;
    }

    public boolean matchesOrder(int order) {
        return gameOrder == order;
    }

    public Name name() {
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
}
