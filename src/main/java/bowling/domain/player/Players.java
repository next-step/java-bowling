package bowling.domain.player;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames) {
        return new Players(playerNames.stream()
                .map(name -> new Player(new PlayerName(name)))
                .collect(toList()));
    }

    public boolean isAllFinished() {
        return lastPlayer().isFramesFinished();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    private Player lastPlayer() {
        return players.get(players.size() - 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Players)) return false;

        Players players1 = (Players) o;

        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return players != null ? players.hashCode() : 0;
    }
}
