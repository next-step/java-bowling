package bowling.model;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private final List<Player> players = new ArrayList<>();

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean isGameOver() {
        return getLastPlayer().isGameOver();
    }

    private Player getLastPlayer() {
        return players.get(getLastIndex());
    }

    private int getLastIndex() {
        return players.size() - 1;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
