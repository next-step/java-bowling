package bowling;

import bowling.domain.BowlingGame;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {

        String player = InputView.inputPlayerName();
        BowlingGame bowlingGame = new BowlingGame(player);

        printBowlingGame(bowlingGame);

        while (bowlingGame.isNotEnd()) {
            int fallenPin = InputView.nextFallenPin(bowlingGame);
            bowlingGame.roll(fallenPin);

            printBowlingGame(bowlingGame);
        }

    }

    private static void printBowlingGame(BowlingGame bowlingGame) {
        ResultView.printFrame();
        ResultView.printFrameByPlayer(bowlingGame);
        ResultView.printScoreByPlayer(bowlingGame);
    }

}
