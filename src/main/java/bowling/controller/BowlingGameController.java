package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public static void run() {
        BowlingGame bowlingGame = new BowlingGame(InputView.playerName());
        ResultView.printScoreBoard(bowlingGame.scoreBoard());
        while(!bowlingGame.isDone()){
            int currentFrame = bowlingGame.currentFrameNumber();
            String pintCounts = InputView.pintCounts(currentFrame);
            bowlingGame.play(pintCounts);
            ResultView.printScoreBoard(bowlingGame.scoreBoard());
        }




    }
}
