package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    public void run(){

        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        String playerName = inputView.inputName();
        BowlingGame game = new BowlingGame(playerName);

        resultView.printEmptyResult(playerName);

        while (!game.isEnd()) {
            int count = inputView.inputPinCount(game.getFrameCount());
            game.play(count);
            resultView.printResult(playerName, game.getFrames().getFrames(), game.getScore());
        }
    }
}
