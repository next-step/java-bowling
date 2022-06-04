package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Pins;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingGameController {
    public static void main(String[] args) {
        Player player = new Player(InputView.scanPlayerName());
        BowlingGame bowlingGame = BowlingGame.create(player);
        OutputView.printBowlingGameResult(bowlingGame);
        while (bowlingGame.isRunning()) {
            int currentFrameNumber = bowlingGame.getCurrentFrameNumber();
            bowlingGame.bowl(Pins.create(InputView.scanHitPins(currentFrameNumber)));
            OutputView.printBowlingGameResult(bowlingGame);
            bowlingGame.nextFrame();
        }
    }
}