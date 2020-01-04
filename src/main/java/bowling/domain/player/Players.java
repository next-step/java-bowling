package bowling.domain.player;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);
    }

    public boolean hasPlayablePlayer() {
        return true;
    }

    public List<Player> getValue() {
        return players;
    }
}
