package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    List<Player> players;

    public Players(List<String> players) {
        this.players = players.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }


}
