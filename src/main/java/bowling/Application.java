package bowling;

import bowling.domain.controller.BowlingGame;
import bowling.domain.pin.BowlCount;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        final Player player = inputView.inputPlayerName();
        final BowlingGame bowlingGame = new BowlingGame(player);

        OutputView.printOverHead(bowlingGame.overHead());
        while (!bowlingGame.isEnd()) {
            BowlCount bowlCount = inputView.inputBowlCount(bowlingGame.getFrameNumber());
            bowlingGame.play(bowlCount);
            OutputView.printOverHead(bowlingGame.overHead());
        }
    }
}
