package step2.controller;

import step2.domain.Player;
import step2.view.InputView;
import step2.view.ResultView;

public class BowlingController {

    public void start() {
        InputView inputView = new InputView();

        Player player = inputView.getPlayer();

        ResultView resultView = new ResultView(player);

        resultView.printEmptyRecords();
    }

}
