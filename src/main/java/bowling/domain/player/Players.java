package bowling.domain.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Players {

    private final List<Player> players;

    private Players() {
        this(new ArrayList<>());
    }

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players create() {
        return new Players();
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public void add(Player player) {
        players.add(player);
    }

    public List<Player> value() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
