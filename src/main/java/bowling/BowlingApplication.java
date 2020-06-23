package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        Player player = Player.of(InputView.inputPlayer());
        BowlingGame bowlingGame = BowlingGame.of(player);

        ResultView.printHeader(bowlingGame);

        while (!bowlingGame.isGameOver()) {
            int hitCount = InputView.inputHitCount(bowlingGame.getFrameNumber());
            bowlingGame.play(hitCount);
            ResultView.printShape(bowlingGame);
        }
    }
}
