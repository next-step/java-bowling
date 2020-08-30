package bowling.domian.player;

import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
         this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public boolean isGameEnd() {
        return players.stream()
                .filter(Player::isGameEnd)
                .count() == players.size();
    }
}
