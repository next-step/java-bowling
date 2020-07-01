package bowling;

import bowling.domain.GameSet;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        Player player = Player.of(InputView.inputPlayer());
        GameSet gameSet = GameSet.of(player);

        ResultView.printHeader(gameSet);

        while (!gameSet.isGameOver()) {
            int hitCount = InputView.inputHitCount(gameSet.getFrameNumber());
            gameSet.play(hitCount);
            ResultView.printShape(gameSet);
        }
    }
}
