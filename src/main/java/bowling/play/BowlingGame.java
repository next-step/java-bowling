package bowling.play;

import bowling.domain.Player;

import java.util.function.Consumer;
import java.util.function.Function;

public class BowlingGame {

    private Player player;

    public BowlingGame(Player player) {
        this.player = player;
    }

    public void round(Function<String, String> rollingBall, Consumer<Player> showResult) {
        while (!player.isEndGame()) {
            player.bowl(rollingBall);
            showResult.accept(player);
        }
    }
}
