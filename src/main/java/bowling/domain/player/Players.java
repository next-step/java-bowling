package bowling.domain.player;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players = new ArrayList<>();

    public void add(Player player) {
        players.add(player);
    }

    public boolean hasPlayablePlayer() {
        for (Player player : players) {
            if (!player.isEnd()) {
                return true;
            }
        }

        return false;
    }

    public List<Player> getValue() {
        return players;
    }
}
