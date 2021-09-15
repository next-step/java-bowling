package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.UserName;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public static void startBowling() {
        UserName userName = InputView.getUserName();

        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.start();

        while (!bowlingGame.isFinished()) {
            InputView.enterBowlPins(bowlingGame.currentFrame());
            bowlingGame.next();
            ResultView.printHeader();
            ResultView.printResult(userName, bowlingGame.getFrames());
        }
    }
}
