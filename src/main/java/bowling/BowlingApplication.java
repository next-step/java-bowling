package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.view.ResultView;

import java.util.Random;

public class BowlingApplication {
    public static void main(String[] args) {
//        Player player = InputView.inputPlayer();
        Player player = new Player("osc");

        BowlingGame game = new BowlingGame(player);

        int inputDonwPinCOunt;
        while (!game.isEndGame()) {
//            inputDonwPinCOunt = InputView.inputDownPinCount(1);
//            inputDonwPinCOunt = 10;
            inputDonwPinCOunt = new Random().nextInt(11);
            game.doGame(inputDonwPinCOunt);
            ResultView.printScoreBoard(game);
        }
    }
}
