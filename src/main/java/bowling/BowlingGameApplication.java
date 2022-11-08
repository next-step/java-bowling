package bowling;

import bowling.controller.BowlingGameController;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingGameApplication {
    public static void main(String[] args) {
        new BowlingGameController(new InputView(), new ResultView()).run();
    }
}
