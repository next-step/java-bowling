package bowling;

import bowling.controller.BowlingGame;
import bowling.controller.BowlingGame2;
import bowling.domain.pin.BowlCount;
import bowling.domain.player.Player;
import bowling.view.InputView;
import bowling.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        final Player player = inputView.inputPlayerName();
        final BowlingGame2 bowlingGame = new BowlingGame2(player);

        OutputView.printOverHead(bowlingGame);
//        while (!bowlingGame.isEnd()) {
//            BowlCount bowlCount = inputView.inputBowlCount(bowlingGame.getFrameNumber());
//            bowlingGame.play(bowlCount);
//            //OutputView.printOverHead(bowlingGame.overHead());
//        }
    }
}
