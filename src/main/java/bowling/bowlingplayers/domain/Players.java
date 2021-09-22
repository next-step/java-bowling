package bowling.bowlingplayers.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Players {

    private final List<Player> players;

    public Players(List<String> playersNames) {
        players = new ArrayList<>();

        for (String name : playersNames) {
            Player player = new Player(name);
            players.add(player);
        }
    }

    public void pitch(int i) {

    }

    public List<Player> players() {
        return Collections.unmodifiableList(players);
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
        return Objects.hash(players);
    }
}
