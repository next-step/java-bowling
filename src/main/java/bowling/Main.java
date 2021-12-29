package bowling;

import bowling.controller.BowlingGame;

public class Main {
    public static void main(String[] args) {
        BowlingGame game = new BowlingGame();
        game.testPlay();
        game.testFullFill();
//        game.testPerfect();
    }
}
