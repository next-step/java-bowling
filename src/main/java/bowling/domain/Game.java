package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> player;

    public void add(Player player) {
        this.player.add(player);
    }

    public Game() {
        player = new ArrayList<>();
    }

    public void play( int numberOfPin, int index) {
        player.get(index).processPin(numberOfPin);
    }

    public Player getPlay(int index) {
        return this.player.get(index);
    }
}
