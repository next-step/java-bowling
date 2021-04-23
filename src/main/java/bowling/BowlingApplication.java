package bowling;

import bowling.controller.BowlingController;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        new BowlingController(new InputView(), new ResultView()).run();
    }
}
