package bowling;

import bowling.controller.BowlingGame;

public class Client {
    public static void main(String[] args) {

        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame.start();
    }
}
