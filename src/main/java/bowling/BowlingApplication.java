package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        Player player = InputView.inputPlayer();
        BowlingGame game = new BowlingGame(player);

        int inputDownPinCount;
        while (!game.isEndGame()) {
            inputDownPinCount = InputView.inputDownPinCount(game.getFrameRound());
            game.doGame(inputDownPinCount);
            ResultView.printScoreBoard(game);
        }
    }
}
