package bowling;


import java.util.List;
import java.util.function.Consumer;

import static bowling.CommonConstans.MINUS_SIZE_ONE;
import static bowling.CommonConstans.MIN_SIZE_UNDER;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<Player> players) {
        validMinSize(players);
        return new Players(players);
    }

    public int countOfPlayers() {
        return players.size();
    }

    public int index(Player player) {
        return players.indexOf(player);
    }

    public void forEach(Consumer<? super Player> players) {
        this.players.forEach(players);
    }

    private static void validMinSize(List<Player> players) {
        if (players.size() < MINUS_SIZE_ONE) {
            throw new IllegalArgumentException(MIN_SIZE_UNDER);
        }
    }
}

