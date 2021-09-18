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
            bowlPins(bowlingGame);
            bowlingGame.next();
            ResultView.printHeader();
            ResultView.printResult(userName, bowlingGame.getFrames());
        }
    }

    private static void bowlPins(BowlingGame bowlingGame) {
        try {
            int downPins = InputView.enterBowlPins(bowlingGame.currentFrame());
            bowlingGame.currentFrame().bowl(downPins);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            bowlPins(bowlingGame);
        }
    }
}
