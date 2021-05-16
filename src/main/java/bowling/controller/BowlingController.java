package bowling.controller;

import bowling.domain.BowlingGame;
import bowling.view.InputView;
import bowling.view.OutputView;

public class BowlingController {

    private InputView inputView = new InputView();
    private OutputView outputView = new OutputView();

    public void play() {
        BowlingGame bowlingGame = new BowlingGame(inputView.getName());
        while (!bowlingGame.isEnd()) {
            bowlingGame.play(inputView.getPoint(0));
        }
    }
}
