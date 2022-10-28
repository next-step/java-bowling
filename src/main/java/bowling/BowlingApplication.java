package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.ResultView;

import java.util.Random;

public class BowlingApplication {
    public static void main(String[] args) {
//        Player player = InputView.inputPlayer();
//        BowlingGame game = new BowlingGame(player);
        BowlingGame game = new BowlingGame(new Player("OSC"));

        int inputDownPinCount;
        while (!game.isEndGame()) {
//            inputDownPinCount = InputView.inputDownPinCount(game.getFrameRound());
            inputDownPinCount = new Random().nextInt(6);
            game.doGame(inputDownPinCount);
            ResultView.printScoreBoard(game);
        }
    }
}
