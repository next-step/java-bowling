package bowling;

import bowling.controller.BowlingController;

public class BowlingGame {

    public static void main(String[] args) {
        BowlingController bowlingController = new BowlingController();
        bowlingController.start();
    }
}
