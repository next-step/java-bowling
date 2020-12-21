package bowling;

import bowling.controller.GameController;

public class Bowling {

    public static void main(String[] args) {
        GameController gameController = GameController.start();
        gameController.play();
    }
}
