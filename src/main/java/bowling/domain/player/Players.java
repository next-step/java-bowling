package bowling.domain.player;

import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public boolean gameOver() {
        return players.stream()
                .allMatch(Player::gameOver);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
