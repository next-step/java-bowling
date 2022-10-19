package bowling.step2.domain;

import java.util.Collections;
import java.util.List;

public class PlayersDTO {
    private final List<Player> players;
    
    public PlayersDTO(final List<Player> players) {
        this.players = players;
    }
    
    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
