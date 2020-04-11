package bowling.domain.player;

import java.util.Objects;

public class Player {
    private final PlayerName playerName;

    private Player(final PlayerName playerNmae) {
        this.playerName = playerNmae;
    }

    public static Player of(final String name) {
        return new Player(PlayerName.of(name));
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Player player = (Player) o;
        return Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName);
    }
}
