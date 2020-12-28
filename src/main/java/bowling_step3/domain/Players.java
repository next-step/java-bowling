package bowling_step3.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private List<Player> players = new ArrayList<>();

    public Players() {

    }

    public List<Player> getPlayers() {
        return players;
    }

    public void add(Player player) {
        players.add(player);
    }
}
