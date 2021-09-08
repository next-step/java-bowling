package step2;

import step2.controller.BowlingGameController;
import step2.view.InputView;

public class BowlingApp {
    public static void main(String[] args) {
        BowlingGameController bowlingGameController = new BowlingGameController(InputView.requirePlayerName());
        bowlingGameController.run();
    }
}
