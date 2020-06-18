package bowling.step4.domain;

import java.util.List;
import java.util.stream.Stream;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public Stream<Player> stream() {
        return players.stream();
    }
}