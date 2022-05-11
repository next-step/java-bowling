package bowling.controller;

import bowling.model.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameController {

    private final InputView inputView;

    private final ResultView resultView;

    public BowlingGameController() {
        this(new InputView(), new ResultView());
    }

    public BowlingGameController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void start() {
        Player player = inputView.inputPlayerName();
        resultView.printHead();
    }

}
