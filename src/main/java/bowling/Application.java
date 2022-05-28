package bowling;

import bowling.controller.BowlingController;

public class Application {
    public static void main(String[] args) {
        BowlingController bowlingController = new BowlingController();
        bowlingController.run();
    }
}
