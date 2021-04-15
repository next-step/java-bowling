package bowling;

import bowling.controller.BowlingGameController;

public class BowlingApplication {
    public static void main(String[] args) {
        BowlingGameController controller = new BowlingGameController();
        controller.run();
    }
}
