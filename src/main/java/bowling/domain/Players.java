package bowling.domain;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        return new Players(players);
    }

    public int howManyPlayers() {
        return players.size();
    }

    public List<Player> players() {
        return Collections.unmodifiableList(players);
    }
}
