package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public static void run() {
        BowlingGame bowlingGame = new BowlingGame(InputView.bowlingGameRequest());
        ResultView.printScoreBoard(bowlingGame.scoreBoard());
        while (!bowlingGame.isDone()) {
            int currentFrameNumber = bowlingGame.currentFrameNumber();
            int pintCount = InputView.pinCount(currentFrameNumber);
            bowlingGame.play(pintCount);
            ResultView.printScoreBoard(bowlingGame.scoreBoard());
        }
    }
}
