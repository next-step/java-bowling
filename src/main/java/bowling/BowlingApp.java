package bowling;

import bowling.controller.BowlingGameController;
import bowling.view.InputView;

public class BowlingApp {
    public static void main(String[] args) {
        BowlingGameController bowlingGameController = new BowlingGameController(InputView.requirePlayerName());
        bowlingGameController.run();
    }
}
