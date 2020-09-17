package bowling;

import bowling.controller.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        BowlingGame bowlingGame = new BowlingGame(inputView, resultView);
        bowlingGame.start();
    }
}
