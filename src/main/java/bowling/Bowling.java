package bowling;

import bowling.controller.BowlingController;
import bowling.view.InputView;

public class Bowling {
    public static void main(String[] args) {
        BowlingController.start(InputView.inputLetters(InputView.inputUserNumber()));
    }
}
