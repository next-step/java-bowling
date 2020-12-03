package step2;

import step2.controller.BowlingController;
import step2.view.BowlingInputView;
import step2.view.BowlingResultView;

public class Main {
    public static void main(String[] args) {
        BowlingController controller = new BowlingController(new BowlingResultView(), new BowlingInputView());
        controller.gameStart();
    }
}
