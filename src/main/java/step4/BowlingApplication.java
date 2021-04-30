package step4;

import step4.controller.BowlingController;
import step4.view.InputView;
import step4.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        BowlingController bowlingController = new BowlingController(inputView, resultView);
        bowlingController.run();
    }
}
