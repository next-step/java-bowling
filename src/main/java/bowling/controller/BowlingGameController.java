package bowling.controller;

import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {
    private final InputView inputView;
    private final ResultView resultView;

    public BowlingGameController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {

    }
}
