package bowling.domain;

import java.util.List;
import java.util.function.Consumer;

public class BowlingGame {
    private final List<Player> players;

    public BowlingGame(List<Player> players) {
        this.players = players;
    }

    public void forEachPlayer(Consumer<? super Player> consumer) {
        players.forEach(consumer);
    }
}
