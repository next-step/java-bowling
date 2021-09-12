package step3;

import step3.controller.BowlingGameController;
import step3.view.InputView;

public class BowlingApp {
    public static void main(String[] args) {
        BowlingGameController bowlingGameController = new BowlingGameController(InputView.requirePlayerName());
        bowlingGameController.run();
    }
}
