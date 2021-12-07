package bowling;

import bowling.controller.BowlingController;
import bowling.domain.factory.FrameFactory;
import bowling.ui.InputView;
import bowling.ui.ResultView;

public class BowlingApplication {

    public static void main(String[] args) {
        BowlingController bowlingController = new BowlingController(new InputView(), new ResultView(),
                                                                    new FrameFactory());
        bowlingController.start();
    }
}
