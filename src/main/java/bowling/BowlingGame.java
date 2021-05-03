package bowling;

import bowling.controller.BowlingGameController;

public class BowlingGame {
    public static void main(String[] args) {
        BowlingGameController controller = new BowlingGameController();
        controller.run();
    }
}
