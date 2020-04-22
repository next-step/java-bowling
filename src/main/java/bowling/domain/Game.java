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

    public void play(Score numberOfPin, int index) {
        player.get(index).processPin(numberOfPin);
    }

    public Player getPlay(int index) {
        return this.player.get(index);
    }

    public boolean isEndGame() {
        return player.stream()
                .anyMatch(Player::isEndGame);
    }
}
