package bowling;

import bowling.controller.BowlingController;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        BowlingController bowlingController = new BowlingController(inputView, resultView);
        bowlingController.run();
    }
}
