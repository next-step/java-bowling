package bowling;

import bowling.controller.BowlingController;

public class BowlingApplication {
    public static void main(String[] args) {
        BowlingController bowlingController = new BowlingController();
        bowlingController.play();
    }
}
