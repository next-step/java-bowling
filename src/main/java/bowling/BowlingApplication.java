package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
//        Player player = InputView.inputPlayer();
        Player player = new Player("osc");

        BowlingGame game = new BowlingGame(player);

        int inputDownPinCount;
        while (!game.isEndGame()) {
            inputDownPinCount = InputView.inputDownPinCount(game.getFrameRound());
//            inputDownPinCount = 10;
//            inputDownPinCount = new Random().nextInt(6);
            game.doGame(inputDownPinCount);
            ResultView.printScoreBoard(game);
        }
    }
}
