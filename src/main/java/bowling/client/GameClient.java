package bowling.client;

import bowling.controller.GameController;

public class GameClient {
    public static void main(String args[]) {
        GameController gameController = new GameController();
        gameController.play();
    }
}
