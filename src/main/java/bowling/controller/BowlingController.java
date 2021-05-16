package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    public void play() {
        BowlingGame bowlingGame = new BowlingGame(inputView.getName());
        outputView.printGame(bowlingGame);
        while (!bowlingGame.isEnd()) {
            bowlingGame.play(inputView.getPoint(bowlingGame.frameCount()));
            outputView.printGame(bowlingGame);
        }
    }
}
