package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public void startGame() {
        BowlingGame bowlingGame = new BowlingGame(InputView.inputPlayerName());

        ResultView.printInit(bowlingGame);
        while (!bowlingGame.isEnd()) {
            int countOfPins = InputView.inputFramePitching(bowlingGame);
            bowlingGame.pitch(countOfPins);
            ResultView.printResult(bowlingGame);
        }
    }
}
