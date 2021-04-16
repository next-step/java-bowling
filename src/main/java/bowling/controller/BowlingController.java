package bowling.controller;

import bowling.domain.Game;
import bowling.domain.Name;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    private InputView inputView = new InputView();
    private ResultView resultView = new ResultView();

    public void playBowling() {
        Name name = new Name(inputView.getName());
        Game game = new Game();
        while (!game.ended()) {
            game.throwBall(inputView.getPoint(game.frameCount()));
            resultView.printScoreBoard(game, name);
        }
    }
}
