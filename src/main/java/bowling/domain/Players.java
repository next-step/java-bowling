package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private List<Player> players;

    private Players(String[] playerNames){
        this.players = Arrays.stream(playerNames)
                .map(playerName -> Player.from(playerName))
                .collect(Collectors.toList());
    }

    public static Players from(String[] playerNames){
        return new Players(playerNames);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
