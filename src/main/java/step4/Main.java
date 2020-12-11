package step4;

import step4.controller.BowlingController;
import step4.view.BowlingInputView;
import step4.view.BowlingResultView;

public class Main {
    public static void main(String[] args) {
        BowlingController controller = new BowlingController(new BowlingResultView(), new BowlingInputView());
        controller.gameStart();
    }
}
