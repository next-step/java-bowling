package bowling.domain.player;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {
    private List<Player> players;

    public Players(List<String> playerNames){
        players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean finish(){
        return players.get(players.size() -1).finish();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Players players1 = (Players) o;
        return Objects.equals(players, players1.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players);
    }
}
