package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players() {
        players = new ArrayList<>();
    }

    public void add(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player get(int playerIndex) {
        return players.get(playerIndex);
    }

    public int size() {
        return players.size();
    }
}
