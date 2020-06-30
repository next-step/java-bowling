package bowling;

import bowling.controller.BowlingGameController;

public class BowlingGameLauncher {

    public static void main(String[] args) {
        BowlingGameController bowlingGameController = new BowlingGameController();
        bowlingGameController.startBowling();
    }

}
