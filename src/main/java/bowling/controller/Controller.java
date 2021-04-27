package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;


public class Controller {

    public static void startBowling() {
        String bowler = InputView.inputUserNames();
        BowlingGame game = new BowlingGame(bowler);

        ResultView.empty(bowler);
        while (!game.isEnd()) {
            int count = InputView.inputPinCount(game.getFrameNumber());
            game.bowl(count);
            ResultView.printScoreBoard(bowler, game.getFrames(), game.getScore());
        }
    }
}
