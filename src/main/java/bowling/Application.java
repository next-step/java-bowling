package bowling;

import bowling.controller.BowlingGame;
import bowling.view.InputView;

public class Application {
    public static void main(String[] args) {
        new BowlingGame().play(new InputView());
    }
}
