package bowling;

import bowling.controller.BowlingGameController;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingApplication {
    public static void main(String[] args) {
        BowlingGameController controller = new BowlingGameController(new InputView(), new ResultView());
        controller.run();
    }
}
