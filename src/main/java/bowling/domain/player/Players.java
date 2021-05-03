package bowling.domain.player;

import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Player> toList() {
        return Collections.unmodifiableList(players);
    }

    public boolean isAllDone() {
        return players.stream()
            .allMatch(Player::isDone);
    }
}
