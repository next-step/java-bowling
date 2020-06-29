package bowling;

import bowling.application.BowlingGame;
import bowling.view.BowlingGameController;

public class Application {

    public static void main(String[] args) {
        BowlingGame bowlingGame = new BowlingGame();
        BowlingGameController gameController = new BowlingGameController(bowlingGame);
        gameController.playGame();
    }
}
