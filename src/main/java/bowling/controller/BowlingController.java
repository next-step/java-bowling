package bowling.controller;

import bowling.domain.Player;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public void start() {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        Player player = inputView.inputPlayer();
        resultView.printFrames(player.getName(), 0);

        inputView.inputScore();
    }
}
