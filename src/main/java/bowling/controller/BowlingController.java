package bowling.controller;

import bowling.domain.Game;
import bowling.domain.Point;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    private InputView inputView = new InputView();
    private ResultView resultView = new ResultView();

    public void playBowling() {
        Game game = new Game();
        while (!game.ended()) {
            game.throwBall(inputView.getPoint());
            resultView.printScoreBoard(game);
        }
    }
}
