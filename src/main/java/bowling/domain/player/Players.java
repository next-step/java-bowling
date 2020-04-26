package bowling.domain.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players {

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public Players(final List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public Players addPlayer(Player player) {
        List<Player> merge = new ArrayList<>(players);
        merge.add(player);
        return new Players(merge);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
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
