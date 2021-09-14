package bowling.domain;

import java.util.List;
import java.util.Objects;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public String name(int index) {
        return players.get(index).name();
    }

    public int size() {
        return players.size();
    }

    public int participantNumber(Player player) {
        return players.indexOf(player);
    }

    public Player get(int number) {
        return players.get(number);
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
