package bowling_step3.domain;

import java.util.*;

public class Players {
    private LinkedList<Player> players;

    public Players() {
        this.players = new LinkedList<>();
    }

    public void add(Player player) {
        this.players.add(player);
    }

    public LinkedList<Player> players() {
        return this.players;
    }
}
