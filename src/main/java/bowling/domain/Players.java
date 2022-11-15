package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public void add(Player player) {
        players.add(player);
    }

    public int size() {
        return players.size();
    }

    public Player get(int index) {
        return players.get(index);
    }

}
