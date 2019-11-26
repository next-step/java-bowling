package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames) {
        List<Player> players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name));
        }

        return new Players(players);
    }

    public Player playerByIndex(int index) {
        return players.get(index);
    }

    public int size() {
        return players.size();
    }
}
