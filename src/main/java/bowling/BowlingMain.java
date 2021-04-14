package bowling;

import bowling.controller.BowlingController;

public class BowlingMain {
    public static void main(String[] args) {
        BowlingController bowlingController = new BowlingController();
        bowlingController.playBowling();
    }
}
