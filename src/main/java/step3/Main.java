package step3;

import step3.controller.BowlingController;
import step3.view.BowlingInputView;
import step3.view.BowlingResultView;

public class Main {
    public static void main(String[] args) {
        BowlingController controller = new BowlingController(new BowlingResultView(), new BowlingInputView());
        controller.gameStart();
    }
}
