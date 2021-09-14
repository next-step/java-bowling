package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.domain.Frame;
import bowling.domain.Point;
import bowling.domain.UserName;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public static void startBowling() {
        UserName userName = InputView.getUserName();
        System.out.println(userName);

        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.start();

        while (!bowlingGame.isFinished()) {
            Frame frame = bowlingGame.currentFrame();
            Point point = InputView.getPoint(bowlingGame.currentFrame());
            frame.addPoint(point);
            bowlingGame.next();
        }


        ResultView.printHeader();
    }
}
