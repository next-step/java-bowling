package bowling.controller;

import bowling.domain.Player;
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
        try {
            doRun();
        } catch (Exception e) {
            resultView.printError(e.getMessage());
        }
    }

    private void doRun() {
        Player player = new Player(inputView.getPlayerName());
        resultView.printScoreBoard(player.getName());

    }
}
