package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.List;

public class BowlingGameController {
    public static void run() {
        BowlingGame bowlingGame = new BowlingGame(InputView.playerName());

        ResultView.printScoreBoard(bowlingGame.scoreBoard());
//        while(!bowlingGame.isDone()){
//            bowlingGame.play();
//            ResultView.printResult(bowlingGame.scoreBoard());
//        }




    }
}
