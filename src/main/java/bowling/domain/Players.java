package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> players) {
        this.players = createPlayers(players);
    }

    private List<Player> createPlayers(List<String> players) {
        return players.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
