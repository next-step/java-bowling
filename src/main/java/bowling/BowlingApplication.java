package bowling;

import bowling.domain.BowlingGame;
import bowling.domain.frame.Frames;
import bowling.domain.player.Player;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {

        String player = InputView.inputPlayerName();
        BowlingGame bowlingGame = new BowlingGame(new Player(player), new Frames());

        printBowlingGame(bowlingGame);

        bowlingGame.start();
        while (bowlingGame.isNotEnd()) {
            int fallenPin = InputView.nextFallenPin(bowlingGame);
            bowlingGame.roll(fallenPin);

            printBowlingGame(bowlingGame);
            nextFrame(bowlingGame);
        }

    }

    private static void printBowlingGame(BowlingGame bowlingGame) {
        ResultView.printFrame();
        ResultView.printFrameByPlayer(bowlingGame);
    }

    private static void nextFrame(BowlingGame bowlingGame) {
        if (bowlingGame.isNotEnd() && bowlingGame.currentFrame().isEnd()) {
            bowlingGame.nextFrame();
        }
    }

}
