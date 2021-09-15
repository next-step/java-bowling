package bowling.domain;

import java.util.*;

public class Players {
    private final Map<Player, Integer> players;

    private Players(List<Player> playerList) {
        Map<Player, Integer> players = new HashMap<>();

        for (int gameIndex = 0; gameIndex < playerList.size(); gameIndex++) {
            Player player = playerList.get(gameIndex);
            players.put(player, gameIndex);
        }

        this.players = players;
    }

    public static Players of(List<Player> players) {
        return new Players(players);
    }

    public int size() {
        return players.size();
    }

    public int gameIndex(Player player) {
        return players.get(player);
    }

    public Set<Player> list() {
        return players.keySet();
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
