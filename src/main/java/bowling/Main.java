package bowling;

import bowling.controller.BowlingGameController;

public class Main {
    public static void main(String[] args) {
        BowlingGameController bowlingGameController = new BowlingGameController();
        bowlingGameController.start();
    }

}
