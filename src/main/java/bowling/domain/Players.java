package bowling.domain;

import java.util.List;

public class Players {
    public static Players from(List<Player> players) {
        return new Players();
    }

    public int howManyPlayers() {
        return -1;
    }
}
