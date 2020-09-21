package bowling.play;

import bowling.domain.Player;
import bowling.domain.Players;
import bowling.exception.NotExistCurrentFramePlayerException;

import java.util.function.Consumer;
import java.util.function.Function;

public class BowlingGame {

    private Players players;

    public BowlingGame(Players players) {
        this.players = players;
    }

    public void start(Function<String, String> rollingBall, Consumer<Player> showResult) {
        while (!players.isAllPlayerEndGame()) {
            round(rollingBall, showResult);
        }
    }

    private void round(Function<String, String> rollingBall, Consumer<Player> showResult) {
        try {
            Player player = players.turnPlayer();
            player.bowl(rollingBall);
            showResult.accept(player);
        }catch (NotExistCurrentFramePlayerException exception){
            System.out.println("NotExistCurrentFramePlayer!");
        }
    }
}
