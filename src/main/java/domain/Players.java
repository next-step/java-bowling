package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Players {
    private List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public String getPlayerName(int position) {
        Optional<Player> maybePlayer = Optional.ofNullable(players.get(position));
        return maybePlayer.orElseThrow(IllegalArgumentException::new).getName();
    }
}
